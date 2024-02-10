package ru.mystreet.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.resources.ImageResource
import kotlinx.coroutines.flow.MutableStateFlow
import ru.mystreet.map.CameraPosition
import ru.mystreet.map.MapAnimation
import ru.mystreet.map.MapWindow
import ru.mystreet.map.Placemark
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.geomety.ScreenPoint

class MapController {

    private val anchor: MutableStateFlow<MapWindow?> = MutableStateFlow(null)

    val cameraPosition: MutableStateFlow<CameraPosition?> = MutableStateFlow(null)

    private var userImage: ImageResource? = null

    private var pin: Placemark? = null

    var isFollowLocation by mutableStateOf(false)

    private inline fun <T : Any> withAnchor(block: MapWindow.() -> T): T? {
        return anchor.value?.run(block)
    }

    internal fun bindAnchor(map: MapWindow) {
        anchor.value = map
        setMapSizeChangedListener(map)
        setCameraListener(map)
    }

    internal fun unbindAnchor() {
        anchor.value = null
        removeCenterAlignedPin()
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

    fun removeCenterAlignedPin() {
        pin = null
    }

    fun setUserLocation(image: ImageResource) {
    }

    fun followUserLocation() {
    }

    fun unfollowUserLocation() {
    }

    private fun setMapSizeChangedListener(
        anchor: MapWindow,
    ) {
        anchor.addSizeChangedListener { _, _, _ ->
            updatePinLocation(anchor)
            updateCameraPosition(anchor.map.cameraPosition)
        }
    }

    private fun setCameraListener(
        anchor: MapWindow,
    ) {
        anchor.map.addCameraListener { _, position, _, _ ->
            updatePinLocation(position)
            updateCameraPosition(position)
        }
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
    }

}

const val DEFAULT_ANIMATION_DURATION = 0.1f
val defaultAnimation = MapAnimation(MapAnimation.Type.LINEAR, DEFAULT_ANIMATION_DURATION)
