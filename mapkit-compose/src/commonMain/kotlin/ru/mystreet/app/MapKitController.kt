package ru.mystreet.app

import dev.icerock.moko.resources.ImageResource
import kotlinx.coroutines.flow.MutableStateFlow
import ru.mystreet.map.Map
import ru.mystreet.map.MapAnimation
import ru.mystreet.map.Placemark
import ru.mystreet.map.geomety.Point

class MapController {

    private val anchor: MutableStateFlow<Map?> = MutableStateFlow(null)

    private var isMapInitialized = false

    private var userImage: ImageResource? = null

    private inline fun <T : Any> withAnchor(block: Map.() -> T): T? {
        return anchor.value?.run(block)
    }

    private fun initializeMapController() {
        if (isMapInitialized) return

        isMapInitialized = true
    }

    internal fun bindAnchor(map: Map) {
        anchor.value = map
        userImage?.let { map.setUserLocation(it) }
        initializeMapController()
    }

    internal fun unbindAnchor() {
        anchor.value = null
    }

    fun zoom(value: Float, animate: Boolean = false) {
        withAnchor {
            move(
                cameraPosition = cameraPosition.copy(zoom = cameraPosition.zoom + value),
                animation = if (animate) defaultAnimation else null,
            )
        }
    }

    fun move(target: Point, zoom: Float? = null, animate: Boolean = false) {
        withAnchor {
            move(
                cameraPosition = cameraPosition.copy(
                    target = target,
                    zoom = zoom ?: cameraPosition.zoom
                ),
                animation = if (animate) defaultAnimation else null,
            )
        }
    }


    fun addPlacemark(position: Point, image: ImageResource): Placemark? {
        return withAnchor {
            addPlacemark(position, image)
        }
    }

    fun setUserLocation(image: ImageResource) {
        userImage = image
        withAnchor {
            setUserLocation(image)
        }
    }

    fun followUserLocation() {
        withAnchor {
            followUserLocation()
        }
    }

    fun unfollowUserLocation() {
        withAnchor {
            unfollowUserLocation()
        }
    }

}

const val DEFAULT_ANIMATION_DURATION = 0.1f
val defaultAnimation = MapAnimation(MapAnimation.Type.LINEAR, DEFAULT_ANIMATION_DURATION)
