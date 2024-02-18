package ru.mystreet.map.map.component

import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.lifecycle.subscribe
import com.arkivanov.mvikotlin.core.rx.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.get
import ru.mystreet.app.MapController
import ru.mystreet.app.UserLocationProvider
import ru.mystreet.app.locationProvider
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getStore
import ru.mystreet.map.CameraPosition
import ru.mystreet.map.domain.entity.MapConfig
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.map.presentation.MapObjectsStore
import ru.mystreet.uikit.MR

class MapComponent(
    componentContext: DIComponentContext,
    mapConfig: MapConfig,
) : AppComponentContext(componentContext), Map {

    private val coroutineScope = coroutineScope()

    private val store: MapObjectsStore = getStore()

    init {
        val disposable = store.labels(object : Observer<MapObjectsStore.Label> {
            override fun onComplete() {
            }

            override fun onNext(value: MapObjectsStore.Label) {
                when (value) {
                    is MapObjectsStore.Label.OnMapObjectsLoaded -> value.loadedMapObjects.forEach {
                        mapController.addPlacemark(
                            Point(it.latitude, it.longitude),
                            MR.images.user_location
                        )
                    }
                }
            }
        })
        lifecycle.doOnDestroy(disposable::dispose)
    }

    override val mapController: MapController =
        MapController(initialCameraPosition = mapConfig.initialCameraPosition)

    private val userLocationProvider: UserLocationProvider =
        mapController.locationProvider(get())

    private var zoomJob: Job? = null

    private fun onUpdateCameraPosition(cameraPosition: CameraPosition?) {
        if (cameraPosition != null)
            store.accept(MapObjectsStore.Intent.UpdateCameraPosition(cameraPosition))
    }

    init {
        mapController.cameraPosition.onEach {
            onUpdateCameraPosition(it)
        }.flowOn(Dispatchers.Main).launchIn(coroutineScope())
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