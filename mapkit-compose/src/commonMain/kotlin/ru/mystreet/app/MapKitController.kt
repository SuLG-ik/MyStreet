package ru.mystreet.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import ru.sulgik.mapkit.Animation
import ru.sulgik.mapkit.MapKit
import ru.sulgik.mapkit.ScreenPoint
import ru.sulgik.mapkit.geometry.Point
import ru.sulgik.mapkit.map.CameraListener
import ru.sulgik.mapkit.map.CameraPosition
import ru.sulgik.mapkit.map.ClusterListener
import ru.sulgik.mapkit.map.ClusterizedPlacemarkCollection
import ru.sulgik.mapkit.map.ImageProvider
import ru.sulgik.mapkit.map.Map
import ru.sulgik.mapkit.map.MapObject
import ru.sulgik.mapkit.map.MapObjectCollection
import ru.sulgik.mapkit.map.MapObjectTapListener
import ru.sulgik.mapkit.map.MapWindow
import ru.sulgik.mapkit.map.PlacemarkMapObject
import ru.sulgik.mapkit.map.SizeChangedListener
import ru.sulgik.mapkit.map.VisibleRegion
import ru.sulgik.mapkit.user_location.UserLocationLayer
import ru.sulgik.mapkit.user_location.UserLocationObjectListener
import kotlin.time.Duration.Companion.milliseconds

class MapController(
    val initialCameraPosition: CameraPosition?,
    private val onObjectClickListener: (MapObject) -> Boolean,
    private val onBind: (() -> Unit)? = null,
    private val onUnbind: (() -> Unit)? = null,
) {

    private var isInitialized = false

    private val anchor: MutableStateFlow<MapWindow?> = MutableStateFlow(null)
    private var userLocationLayer: UserLocationLayer? = null
    val cameraPosition: MutableStateFlow<CameraPosition?> = MutableStateFlow(null)
    val currentTarget: MutableStateFlow<Point> = MutableStateFlow(Point(0.0, 0.0))

    private var pin: PlacemarkMapObject? = null

    var isFollowLocation by mutableStateOf(false)

    private inline fun <T : Any> withAnchor(block: MapWindow.() -> T): T? {
        return anchor.value?.run(block)
    }

    private val sizeChangedListener =
        SizeChangedListener { window, _, _ ->
            updatePinLocation(window.map.cameraPosition)
            updateCameraPosition(window.map.cameraPosition)
        }

    private val tapListener =
        MapObjectTapListener { mapObject, _ ->
            onObjectClickListener(mapObject)
        }

    private val cameraListener =
        CameraListener { _, cameraPosition, _, _ ->
            updatePinLocation(cameraPosition)
            updateCameraPosition(cameraPosition)
        }

    fun bindAnchor(map: MapWindow) {
        anchor.value = map
        if (!isInitialized && initialCameraPosition != null) {
            map.map.move(initialCameraPosition)
        }
        setMapSizeChangedListener(map)
        setCameraListener(map)
        setTapListener(map)
        onBind?.invoke()
        userLocationLayer = MapKit.getInstance().createUserLocationLayer(map)
    }

    fun unbindAnchor() {
        withAnchor {
            removeCameraListener(this)
            removeMapSizeChangedListener(this)
            removeTapListener(this)
        }
        anchor.value = null
        onUnbind?.invoke()
        userLocationLayer = null
    }

    fun zoom(value: Float, animate: Boolean = false) {
        withAnchor {
            map.move(
                cameraPosition = map.cameraPosition.copy(zoom = map.cameraPosition.zoom + value),
                animation = if (animate) defaultAnimation else null,
            )
        }
    }

    fun move(target: Point, zoom: Float? = null, animate: Boolean = false) {
        withAnchor {
            map.move(
                cameraPosition = map.cameraPosition.copy(
                    target = target,
                    zoom = zoom ?: map.cameraPosition.zoom
                ),
                animation = if (animate) defaultAnimation else null,
            )
        }
    }


    fun addPlacemark(position: Point, image: ImageProvider): PlacemarkMapObject? {
        return withAnchor {
            map.mapObjects.addPlacemark().apply {
                geometry = position
                setIcon(image)
            }
        }
    }

    fun addCenterAlignedPin(image: ImageProvider): PlacemarkMapObject? {
        return pin ?: withAnchor {
            val screenPoint = ScreenPoint(
                width / 2f,
                height / 2f,
            )
            map.mapObjects.addPlacemark().apply {
                geometry = convertScreenToWorld(screenPoint) ?: return null
                setIcon(image)
                pin = this
            }
        }
    }

    fun setUserLocationObjectListener(userLocationObjectListener: UserLocationObjectListener) {
        userLocationLayer?.setObjectListener(userLocationObjectListener)
    }

    fun resetLocationManager() {
        MapKit.getInstance().resetLocationManagerToDefault()
    }

    private var icon: ImageProvider? = null

    private val clusterListener = ClusterListener {
        val icon = icon
        if (icon != null)
            it.appearance.setIcon(icon)
        it.appearance.zIndex = 100f
    }

    fun addClusterizedPlacemark(): ClusterizedPlacemarkCollection? {
        this.icon = icon
        return withAnchor {
            map.mapObjects.addClusterizedPlacemarkCollection(clusterListener)
        }
    }

    fun removePlacemarks(clusterizedPlacemark: ClusterizedPlacemarkCollection) {
        withAnchor {
            map.mapObjects.remove(clusterizedPlacemark)
        }
    }

    private fun setMapSizeChangedListener(
        anchor: MapWindow,
    ) {
        anchor.addSizeChangeListener(sizeChangedListener)
    }

    private fun setCameraListener(
        anchor: MapWindow,
    ) {
        anchor.map.addCameraListener(cameraListener)
    }

    private fun removeMapSizeChangedListener(
        anchor: MapWindow,
    ) {
        anchor.addSizeChangeListener(sizeChangedListener)
    }

    private fun setTapListener(
        anchor: MapWindow,
    ) {
        anchor.map.mapObjects.addTapListener(tapListener)
    }

    private fun removeTapListener(
        anchor: MapWindow,
    ) {
        anchor.map.mapObjects.removeTapListener(tapListener)
    }

    private fun removeCameraListener(
        anchor: MapWindow,
    ) {
        anchor.map.addCameraListener(cameraListener)
    }

    private fun updatePinLocation(anchor: MapWindow) {
        val pin = pin ?: return
        val screenPoint = ScreenPoint(
            anchor.width / 2f,
            anchor.height / 2f,
        )
        pin.geometry = anchor.convertScreenToWorld(screenPoint) ?: return
    }

    private fun updatePinLocation(cameraPosition: CameraPosition) {
        val pin = pin ?: return
        pin.geometry = cameraPosition.target
    }

    private fun updateCameraPosition(cameraPosition: CameraPosition) {
        this.cameraPosition.value = cameraPosition
        this.currentTarget.value = cameraPosition.target
    }

    fun visibleArea(cameraPosition: CameraPosition): VisibleRegion? {
        return withAnchor {
            map.calculateVisibleRegion(cameraPosition)
        }
    }

    fun addCollection(): MapObjectCollection? {
        return withAnchor {
            map.mapObjects.addCollection()
        }
    }

    companion object {
        private const val CLUSTER_RADIUS = 60.0
        private const val CLUSTER_MIN_ZOOM = 15
    }

}

private fun Map.move(cameraPosition: CameraPosition, animation: Animation?) {
    if (animation != null) {
        move(cameraPosition, animation)
    } else {
        move(cameraPosition)
    }
}

val DEFAULT_ANIMATION_DURATION = 100.milliseconds
val defaultAnimation = Animation(Animation.Type.LINEAR, DEFAULT_ANIMATION_DURATION)
