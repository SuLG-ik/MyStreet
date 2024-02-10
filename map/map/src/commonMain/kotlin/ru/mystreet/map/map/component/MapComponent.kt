package ru.mystreet.map.map.component

import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.subscribe
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.get
import ru.mystreet.app.MapController
import ru.mystreet.app.UserLocationProvider
import ru.mystreet.app.locationProvider
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.uikit.MR

class MapComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), Map {

    private val coroutineScope = coroutineScope()

    override val mapController: MapController = MapController()

    private val userLocationProvider: UserLocationProvider =
        mapController.locationProvider(get())

    private var zoomJob: Job? = null

    init {
        lifecycle.subscribe(
            onResume = {
                userLocationProvider.start()
            },
            onStop = {
                userLocationProvider.stop()
            }
        )
    }


    override fun onZoomOutPress(isStart: Boolean) {
        onZoomPress(DEFAULT_ANIMATED_ZOOM_OUT, isStart)
    }

    override fun onZoomInPress(isStart: Boolean) {
        onZoomPress(DEFAULT_ANIMATED_ZOOM_IN, isStart)
    }

    override fun onFollowLocation() {
        if (mapController.isFollowLocation)
            mapController.unfollowUserLocation()
        else
            mapController.followUserLocation()
    }

    private fun onZoomPress(value: Float, isStart: Boolean) {
        zoomJob?.cancel()
        zoomJob = if (isStart) {
            coroutineScope.launch {
                while (true) {
                    mapController.zoom(value, true)
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