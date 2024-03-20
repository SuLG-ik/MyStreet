package ru.mystreet.map.map.component

import ru.mystreet.app.MapController
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.map.BaseMapObject
import ru.mystreet.map.domain.entity.MapConfig
import ru.mystreet.map.domain.entity.MapGeoObject

class MapControlComponent(
    componentContext: DIComponentContext,
    mapConfig: MapConfig,
    private val onMapObjectInfo: (Long) -> Unit,
) : AppComponentContext(componentContext), MapControl {

    override val controller: MapController =
        MapController(
            initialCameraPosition = mapConfig.initialCameraPosition,
            onObjectClickListener = this::onObjectClickListener,
            onBind = this::onBind,
            onUnbind = this::onUnbind,
        )

    override val mapObjects: MapObjects = MapObjectsComponent(componentContext, controller)

    override val mapCamera: MapCamera =
        MapCameraComponent(componentContext, controller) { mapObjects.userLocationPoint }

    private fun onBind() {
        mapObjects.onBind()
        mapCamera.onBind()
    }

    private fun onUnbind() {
        mapObjects.onUnbind()
        mapCamera.onUnbind()
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


}