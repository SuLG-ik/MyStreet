package ru.mystreet.app

import kotlinx.coroutines.flow.MutableStateFlow
import ru.mystreet.map.MapAnimation
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.Map

class MapController {

    private val anchor: MutableStateFlow<Map?> = MutableStateFlow(null)

    private inline fun withAnchor(block: Map.() -> Unit) {
        anchor.value?.apply(block)
    }

    internal fun bindAnchor(map: Map) {
        anchor.value = map
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
                cameraPosition = cameraPosition.copy(target = target, zoom = zoom ?: cameraPosition.zoom),
                animation = if (animate) defaultAnimation else null,
            )
        }
    }


}

const val DEFAULT_ANIMATION_DURATION = 0.1f
val defaultAnimation = MapAnimation(MapAnimation.Type.LINEAR, DEFAULT_ANIMATION_DURATION)
