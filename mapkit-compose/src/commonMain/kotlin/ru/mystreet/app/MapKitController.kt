package ru.mystreet.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.resources.ImageResource
import kotlinx.coroutines.flow.MutableStateFlow
import ru.mystreet.map.BaseMapObject
import ru.mystreet.map.CameraListener
import ru.mystreet.map.CameraPosition
import ru.mystreet.map.ClusterListener
import ru.mystreet.map.ClusterizedPlacemark
import ru.mystreet.map.MapAnimation
import ru.mystreet.map.MapObjectTapListener
import ru.mystreet.map.MapWindow
import ru.mystreet.map.Placemark
import ru.mystreet.map.SizeChangedListener
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.geomety.ScreenPoint
import ru.mystreet.map.geomety.VisibleArea

class MapController(
    val initialCameraPosition: CameraPosition?,
    private val onObjectClickListener: (BaseMapObject) -> Boolean,
) {

    private var isInitialized = false

    private val anchor: MutableStateFlow<MapWindow?> = MutableStateFlow(null)

    val cameraPosition: MutableStateFlow<CameraPosition?> = MutableStateFlow(null)
    val currentTarget: MutableStateFlow<Point> = MutableStateFlow(Point())

    private var userImage: ImageResource? = null

    private var pin: Placemark? = null

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

    internal fun bindAnchor(map: MapWindow) {
        anchor.value = map
        if (!isInitialized && initialCameraPosition != null) {
            map.map.move(initialCameraPosition)
        }
        setMapSizeChangedListener(map)
        setCameraListener(map)
        setTapListener(map)
    }

    internal fun unbindAnchor() {
        withAnchor {
            removeCameraListener(this)
            removeMapSizeChangedListener(this)
            removeTapListener(this)
        }
        anchor.value = null
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


    fun addPlacemark(position: Point, image: ImageResource): Placemark? {
        return withAnchor {
            map.mapObjects.addPlacemark().apply {
                geomety = position
                setIcon(image)
            }
        }
    }

    fun addCenterAlignedPin(image: ImageResource): Placemark? {
        return pin ?: withAnchor {
            val screenPoint = ScreenPoint(
                width / 2f,
                height / 2f,
            )
            map.mapObjects.addPlacemark().apply {
                geomety = screenToWorld(screenPoint) ?: return null
                setIcon(image)
                pin = this
            }
        }
    }

    fun setUserLocation(image: ImageResource) {
    }

    fun followUserLocation() {
    }

    fun unfollowUserLocation() {
    }

    private var icon: ImageResource? = null

    private val clusterListener = ClusterListener {
        val icon = icon
        if (icon != null)
            it.appearance.setIcon(icon)
        it.appearance.zIndex = 100f
    }

    fun addClusterizedPlacemark(): ClusterizedPlacemark? {
        this.icon = icon
        return withAnchor {
            map.mapObjects.addClusterizedPlacemark(clusterListener)
        }
    }

    fun removePlacemarks(clusterizedPlacemark: ClusterizedPlacemark) {
        withAnchor {
            map.mapObjects.removePlacemarks(clusterizedPlacemark)
        }
    }

    private fun setMapSizeChangedListener(
        anchor: MapWindow,
    ) {
        anchor.addSizeChangedListener(sizeChangedListener)
    }

    private fun setCameraListener(
        anchor: MapWindow,
    ) {
        anchor.map.addCameraListener(cameraListener)
    }

    private fun removeMapSizeChangedListener(
        anchor: MapWindow,
    ) {
        anchor.addSizeChangedListener(sizeChangedListener)
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
        pin.geomety = anchor.screenToWorld(screenPoint) ?: return
    }

    private fun updatePinLocation(cameraPosition: CameraPosition) {
        val pin = pin ?: return
        pin.geomety = cameraPosition.target
    }

    private fun updateCameraPosition(cameraPosition: CameraPosition) {
        this.cameraPosition.value = cameraPosition
        this.currentTarget.value = cameraPosition.target
    }

    fun visibleArea(cameraPosition: CameraPosition): VisibleArea? {
        return withAnchor {
            map.visibleArea(cameraPosition)
        }
    }

    companion object {
        private const val CLUSTER_RADIUS = 60.0
        private const val CLUSTER_MIN_ZOOM = 15
    }

}

const val DEFAULT_ANIMATION_DURATION = 0.1f
val defaultAnimation = MapAnimation(MapAnimation.Type.LINEAR, DEFAULT_ANIMATION_DURATION)
