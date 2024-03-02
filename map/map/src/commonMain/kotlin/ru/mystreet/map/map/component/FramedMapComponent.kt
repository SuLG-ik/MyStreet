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
import ru.mystreet.map.BaseMapObject
import ru.mystreet.map.CameraPosition
import ru.mystreet.map.ClusterizedPlacemark
import ru.mystreet.map.IconStyle
import ru.mystreet.map.domain.entity.MapConfig
import ru.mystreet.map.domain.entity.MapGeoObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.geomety.VisibleArea
import ru.mystreet.map.map.domain.entity.MapObjectPart
import ru.mystreet.map.map.presentation.FramedMapObjectsStore

class FramedMapComponent(
    componentContext: DIComponentContext,
    mapConfig: MapConfig,
    private val onMapObjectInfo: (id: Long) -> Unit,
) : AppComponentContext(componentContext), Map {

    private val coroutineScope = coroutineScope()

    private val store: FramedMapObjectsStore = getStore()

    private val clusterizedPlacemarks: MutableMap<MapObjectCategory, ClusterizedPlacemark> =
        mutableMapOf()

    init {
        val disposable = store.labels(object : Observer<FramedMapObjectsStore.Label> {
            override fun onComplete() {
            }

            override fun onNext(value: FramedMapObjectsStore.Label) {
                when (value) {
                    is FramedMapObjectsStore.Label.OnLoaded -> {
                        val groupedMapObjects = value.objects.groupBy(MapObjectPart::category)
                        groupedMapObjects
                            .forEach { (key, placemarks) ->
                                val placemark = clusterizedPlacemarks.getOrPut(key) {
                                    mapController.addClusterizedPlacemark() ?: return@forEach
                                }
                                placemark.addPlacemarks(
                                    points = placemarks.map { it.point },
                                    icon = key.image,
                                    iconStyle = IconStyle(),
                                ).forEachIndexed { index, it ->
                                    it.data = MapGeoObject.MapObject(placemarks[index].id)
                                }
                                placemark.clusterPlacemarks(CLUSTER_RADIUS, CLUSTER_MIN_ZOOM)
                            }
                    }
                }
            }
        })
        lifecycle.doOnDestroy(disposable::dispose)
    }

    override val mapController: MapController =
        MapController(
            initialCameraPosition = mapConfig.initialCameraPosition,
            onObjectClickListener = this::onObjectClickListener,
        )

    override fun setCategories(categories: List<MapObjectCategory>) {
    }

    private fun onObjectClickListener(mapObject: BaseMapObject): Boolean {
        val data = mapObject.data
        if (data is MapGeoObject) {
            when (data) {
                is MapGeoObject.MapObject -> {
                    onMapObjectInfo(data.id)
                    return true
                }
            }
        }
        return false
    }

    private val userLocationProvider: UserLocationProvider =
        mapController.locationProvider(get())

    private var zoomJob: Job? = null

    private fun onUpdateCameraPosition(visibleArea: VisibleArea, cameraPosition: CameraPosition) {
        store.accept(FramedMapObjectsStore.Intent.UpdateCameraPosition(visibleArea, cameraPosition))
    }

    init {
        mapController.cameraPosition.onEach { cameraPosition ->
            if (cameraPosition != null) {
                val visibleArea = mapController.visibleArea(cameraPosition) ?: return@onEach
                onUpdateCameraPosition(visibleArea, cameraPosition)
            }
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

        private const val CLUSTER_RADIUS = 60.0
        private const val CLUSTER_MIN_ZOOM = 15
    }

}