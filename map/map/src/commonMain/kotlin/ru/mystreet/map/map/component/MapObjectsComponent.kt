package ru.mystreet.map.map.component

import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.rx.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.get
import ru.mystreet.app.MapController
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getStore
import ru.mystreet.map.component.MapObjects
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.entity.MapGeoObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.MapObjectPart
import ru.mystreet.map.map.presentation.FramedMapObjectsStore
import ru.mystreet.uikit.MR
import ru.sulgik.mapkit.map.CameraPosition
import ru.sulgik.mapkit.map.ClusterListener
import ru.sulgik.mapkit.map.ClusterizedPlacemarkCollection
import ru.sulgik.mapkit.map.IconStyle
import ru.sulgik.mapkit.map.ImageProvider
import ru.sulgik.mapkit.map.MapObjectCollection
import ru.sulgik.mapkit.map.PlacemarkMapObject
import ru.sulgik.mapkit.map.VisibleRegion
import ru.sulgik.mapkit.map.getCastedUserData
import ru.sulgik.mapkit.moko.MOKOImageLoader

class MapObjectsComponent(
    componentContext: DIComponentContext,
    private val controller: MapController,
) : AppComponentContext(componentContext), MapObjects {

    private var selectedCategories = listOf<MapObjectCategory>()
    private var selectedMapObjectId: Long? = null

    private val images: MOKOImageLoader = get()

    private fun isVisible(mapObject: MapGeoObject.MapObject): Boolean {
        return mapObject.category in selectedCategories
    }

    private fun isSelected(mapObject: MapGeoObject.MapObject): Boolean {
        return mapObject.id == selectedMapObjectId
    }

    private val framedPlacemarks: MutableMap<MapFrame, Pair<ClusterizedPlacemarkCollection, List<PlacemarkMapObject>>> =
        mutableMapOf()

    private var allMapObjects: MapObjectCollection? = null

    private val store: FramedMapObjectsStore = getStore()

    private val userLocationProvider =
        UserLocationImage(images.fromResource(MR.images.user_location))

    override fun onBind() {
        setUserLocation()
        restoreMapObjects()
    }

    private fun setUserLocation() {
        controller.setUserLocationObjectListener(userLocationProvider)
    }

    override fun onUnbind() {
        clearMapObjects()
    }

    override fun setCategories(categories: List<MapObjectCategory>) {
        selectedCategories = categories
        visitPlacemarks()
    }

    private fun visitPlacemarks() {
        framedPlacemarks.forEach { (_, value) ->
            value.second.forEach {
                val data = it.getCastedUserData<MapGeoObject.MapObject>() ?: return
                val scale = if (isSelected(data)) 1.5f else 1f
                it.setIcon(
                    images.fromResource(data.category.image),
                    IconStyle(isVisible = isVisible(data), scale = scale)
                )
            }
        }
    }

    override fun setSelected(mapObjectId: Long?) {
        selectedMapObjectId = mapObjectId
        visitPlacemarks()
    }

    override val userLocationPoint get() = userLocationProvider.iconPoint

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

    private val clusterListener = ClusterListener {
        it.appearance.setIcon(images.fromResource(MR.images.user_location))
        it.appearance.zIndex = 100f
    }

    private fun addMapObjects(objects: FramedMapObjects) {
        val currentClusterizedPlacemark =
            framedPlacemarks.getOrPut(objects.frame) {
                (allMapObjects?.addClusterizedPlacemarkCollection(clusterListener)
                    ?: return) to emptyList()
            }
        val ids = objects.objects.map(MapObjectPart::id)
        val alreadyCreatedPlacemarks =
            currentClusterizedPlacemark.second.mapNotNull { placemark ->
                val data = placemark.getCastedUserData<MapGeoObject.MapObject>() ?: run {
                    currentClusterizedPlacemark.first.remove(placemark)
                    return@mapNotNull null
                }
                if (data.id !in ids) {
                    currentClusterizedPlacemark.first.remove(placemark)
                    null
                } else {
                    data.id to placemark
                }
            }.toMap()
        val newObjects = objects.objects.filterNot { alreadyCreatedPlacemarks.containsKey(it.id) }
            .groupBy(MapObjectPart::category)
            .flatMap { (category, objects) ->
                currentClusterizedPlacemark.first.addPlacemarks(
                    objects.map { it.point },
                    images.fromResource(category.image),
                    IconStyle(isVisible = category in selectedCategories)
                ).onEachIndexed { index, placemark ->
                    placemark.userData = MapGeoObject.MapObject(objects[index].id, category)
                }
            }
        currentClusterizedPlacemark.first.clusterPlacemarks(CLUSTER_RADIUS, CLUSTER_MIN_ZOOM)
        framedPlacemarks[objects.frame] =
            currentClusterizedPlacemark.first to alreadyCreatedPlacemarks.values + newObjects
    }

    private fun restoreMapObjects() {
        allMapObjects = controller.addCollection()
        store.state.loadedObjects.forEach {
            addMapObjects(it)
        }
    }

    private fun clearMapObjects() {
        allMapObjects = null
        framedPlacemarks.clear()
    }

    init {
        controller.cameraPosition.onEach { cameraPosition ->
            if (cameraPosition != null) {
                val visibleArea = controller.visibleArea(cameraPosition) ?: return@onEach
                onUpdateCameraPosition(visibleArea, cameraPosition)
            }
        }.flowOn(Dispatchers.Main).launchIn(coroutineScope())
    }


    private fun onUpdateCameraPosition(visibleArea: VisibleRegion, cameraPosition: CameraPosition) {
        store.accept(FramedMapObjectsStore.Intent.UpdateCameraPosition(visibleArea, cameraPosition))
    }


    companion object {
        const val CLUSTER_RADIUS = 60.0
        const val CLUSTER_MIN_ZOOM = 15
    }


}