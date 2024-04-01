package ru.mystreet.map.map.component

import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.mystreet.app.MapController
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.map.component.MapCamera
import ru.mystreet.map.geomety.Point

class MapCameraComponent(
    componentContext: DIComponentContext,
    private val controller: MapController,
    private val getUserLocation: () -> Point?,
) : AppComponentContext(componentContext), MapCamera {

    private val coroutineScope = coroutineScope()

    private var zoomJob: Job? = null

    override fun onBind() {

    }

    override fun onUnbind() {
        zoomJob?.cancel()
    }

    override fun onZoomOutPress(isStart: Boolean) {
        onZoomPress(DEFAULT_ANIMATED_ZOOM_OUT, isStart)
    }

    override fun onZoomInPress(isStart: Boolean) {
        onZoomPress(DEFAULT_ANIMATED_ZOOM_IN, isStart)
    }

    override fun onFollowLocation() {
        val target = getUserLocation() ?: return
        controller.move(target, DEFAULT_FOLLOW_LOCATION_ZOOM, animate = true)
    }

    private fun onZoomPress(value: Float, isStart: Boolean) {
        zoomJob?.cancel()
        zoomJob = if (isStart) {
            coroutineScope.launch {
                while (true) {
                    controller.zoom(value, true)
                    delay(DEFAULT_PRESS_DELAY)
                }
            }
        } else
            null
    }

    companion object {
        const val DEFAULT_ANIMATED_ZOOM_OUT = -0.25f
        const val DEFAULT_ANIMATED_ZOOM_IN = 0.25f
        const val DEFAULT_PRESS_DELAY = 100L

        const val DEFAULT_FOLLOW_LOCATION_ZOOM = 17f
    }

}