package ru.mystreet.map.map.component

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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

    override val isNecessaryZoomAlert: Flow<Boolean> = mapController.cameraPosition.map { it != null && it.zoom <= 15f }

    override fun setCategories(categories: List<MapObjectCategory>) {
        mapControl.mapObjects.setCategories(categories)
    }

    override fun onLocationPermissionGranted() {
        mapController.resetLocationManager()
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