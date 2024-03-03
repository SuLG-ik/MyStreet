package ru.mystreet.map.map.component

import androidx.collection.MutableScatterMap
import androidx.collection.mutableScatterMapOf
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.rx.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.mystreet.app.MapController
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getStore
import ru.mystreet.map.CameraPosition
import ru.mystreet.map.ClusterizedPlacemark
import ru.mystreet.map.IconStyle
import ru.mystreet.map.domain.entity.MapGeoObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.geomety.VisibleArea
import ru.mystreet.map.map.domain.entity.MapObjectPart
import ru.mystreet.map.map.presentation.FramedMapObjectsStore

class MapObjectsComponent(
    componentContext: DIComponentContext,
    private val controller: MapController,
) : AppComponentContext(componentContext), MapObjects {


    private val clusterizedPlacemarks: MutableScatterMap<MapObjectCategory, ClusterizedPlacemark> =
        mutableScatterMapOf()

    private val store: FramedMapObjectsStore = getStore()

    override fun onBind() {
        clearMapObjects()
    }

    override fun onUnbind() {
        restoreMapObjects()
    }

    override fun setCategories(categories: List<MapObjectCategory>) {

    }

    init {
        val disposable = store.labels(object : Observer<FramedMapObjectsStore.Label> {
            override fun onComplete() {
            }

            override fun onNext(value: FramedMapObjectsStore.Label) {
                when (value) {
                    is FramedMapObjectsStore.Label.OnLoaded -> {
                        addMapObjects(value.objects)
                    }
                }
            }
        })
        lifecycle.doOnDestroy(disposable::dispose)
    }

    private fun addMapObjects(objects: List<MapObjectPart>) {
        val groupedMapObjects = objects.groupBy(MapObjectPart::category)
        groupedMapObjects
            .forEach { (key, placemarks) ->
                val placemark = clusterizedPlacemarks.getOrPut(key) {
                    controller.addClusterizedPlacemark() ?: return@forEach
                }
                placemark.addPlacemarks(
                    points = placemarks.map { it.point },
                    icon = key.image,
                    iconStyle = IconStyle(),
                ).forEachIndexed { index, it ->
                    it.data = MapGeoObject.MapObject(placemarks[index].id)
                }
                placemark.clusterPlacemarks(
                    CLUSTER_RADIUS,
                    CLUSTER_MIN_ZOOM
                )
            }
    }

    private fun restoreMapObjects() {
        addMapObjects(store.state.loadedObjects.flatMap { it.objects })
    }

    private fun clearMapObjects() {
        clusterizedPlacemarks.clear()
    }

    init {
        controller.cameraPosition.onEach { cameraPosition ->
            if (cameraPosition != null) {
                val visibleArea = controller.visibleArea(cameraPosition) ?: return@onEach
                onUpdateCameraPosition(visibleArea, cameraPosition)
            }
        }.flowOn(Dispatchers.Main).launchIn(coroutineScope())
    }


    private fun onUpdateCameraPosition(visibleArea: VisibleArea, cameraPosition: CameraPosition) {
        store.accept(FramedMapObjectsStore.Intent.UpdateCameraPosition(visibleArea, cameraPosition))
    }


    companion object {
        private const val CLUSTER_RADIUS = 60.0
        private const val CLUSTER_MIN_ZOOM = 15
    }


}