package ru.mystreet.app.component.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.get
import ru.mystreet.app.MapController
import ru.mystreet.app.MapView
import ru.mystreet.app.context.BaseComponent
import ru.mystreet.app.context.DIComponentContext
import ru.mystreet.app.context.coroutineScope
import ru.mystreet.map.location.LocationManager
import ru.mystreet.map.location.LocationUpdateEvent

class MapComponent(
    componentContext: DIComponentContext
) : BaseComponent(componentContext), Map {

    private val coroutineScope = coroutineScope()

    private val mapController: MapController = MapController()

    private val locationManager: LocationManager = get()

    private var zoomJob: Job? = null

    private var followLocationJob: Job? = null
    private var isFollowLocationZoomChanged: Boolean = false

    @Composable
    override fun Content(modifier: Modifier) {
        Box {
            MapView(
                mapController = mapController,
                modifier = Modifier.fillMaxSize(),
            )
            MapOverlay(
                onFollowLocation = ::onFollowLocation,
                onZoomInPress = ::onZoomInPress,
                onZoomOutPress = ::onZoomOutPress,
                modifier = modifier,
            )
        }
    }

    private fun onZoomOutPress(isStart: Boolean) {
        onZoomPress(DEFAULT_ANIMATED_ZOOM_OUT, isStart)
    }

    private fun onZoomInPress(isStart: Boolean) {
        onZoomPress(DEFAULT_ANIMATED_ZOOM_IN, isStart)
    }

    private fun onFollowLocation() {
        stopFollowLocation()
        followLocationJob = coroutineScope.launch {
            locationManager.locationUpdateFlow().collect {
                when (it) {
                    is LocationUpdateEvent.LocationStatusUpdate -> {}
                    is LocationUpdateEvent.LocationUpdate -> mapController.move(
                        target = it.location.position,
                        zoom = if (!isFollowLocationZoomChanged) DEFAULT_FOLLOW_LOCATION_ZOOM else null,
                        animate = true,
                    )
                }
            }
        }
    }

    private fun stopFollowLocation() {
        followLocationJob?.cancel()
        isFollowLocationZoomChanged = false
    }

    private fun updateFloatingFollowLocationZoom() {
        if (followLocationJob != null) {
            isFollowLocationZoomChanged = true
        }
    }

    private fun onZoomPress(value: Float, isStart: Boolean) {
        zoomJob?.cancel()
        zoomJob = if (isStart) {
            stopFollowLocation()
            coroutineScope.launch {
                while (true) {
                    stopFollowLocation()
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