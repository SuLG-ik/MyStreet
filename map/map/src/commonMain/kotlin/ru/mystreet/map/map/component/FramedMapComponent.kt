package ru.mystreet.map.map.component

import ru.mystreet.app.MapController
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext
import ru.mystreet.map.domain.entity.MapConfig
import ru.mystreet.map.domain.entity.MapObjectCategory

class FramedMapComponent(
    componentContext: DIComponentContext,
    mapConfig: MapConfig,
    onMapObjectInfo: (id: Long) -> Unit,
) : AppComponentContext(componentContext), Map {

    private val mapControl: MapControl = MapControlComponent(
        componentContext = diChildContext("map_control"),
        mapConfig = mapConfig,
        onMapObjectInfo = onMapObjectInfo
    )

    override val mapController: MapController = mapControl.controller

    override fun setCategories(categories: List<MapObjectCategory>) {
        mapControl.mapObjects.setCategories(categories)
    }

    override fun onFollowLocation() {
        mapControl.mapCamera.onFollowLocation()
    }

    override fun onZoomOutPress(isStart: Boolean) {
        mapControl.mapCamera.onZoomOutPress(isStart)
    }

    override fun onZoomInPress(isStart: Boolean) {
        mapControl.mapCamera.onZoomInPress(isStart)
    }

}