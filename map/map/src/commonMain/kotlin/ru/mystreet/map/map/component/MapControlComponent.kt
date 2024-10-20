package ru.mystreet.map.map.component

import ru.mystreet.app.MapController
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.map.component.MapCamera
import ru.mystreet.map.component.MapControl
import ru.mystreet.map.component.MapObjects
import ru.mystreet.map.domain.entity.CameraPositionConfig
import ru.mystreet.map.domain.entity.MapConfig
import ru.mystreet.map.domain.entity.MapGeoObject
import ru.mystreet.map.domain.entity.PointConfig
import ru.sulgik.mapkit.geometry.Latitude
import ru.sulgik.mapkit.geometry.Longitude
import ru.sulgik.mapkit.geometry.Point
import ru.sulgik.mapkit.map.CameraPosition
import ru.sulgik.mapkit.map.MapObject

class MapControlComponent(
    componentContext: DIComponentContext,
    mapConfig: MapConfig,
    private val onMapObjectInfo: (Long) -> Unit,
) : AppComponentContext(componentContext), MapControl {

    override val controller: MapController =
        MapController(
            initialCameraPosition = mapConfig.initialCameraPosition?.toMap(),
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

    private fun onObjectClickListener(mapObject: MapObject): Boolean {
        val data = mapObject.userData
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

private fun CameraPositionConfig.toMap(): CameraPosition {
    return CameraPosition(
        target = target.toMap(), zoom = zoom, azimuth = azimuth, tilt = tilt,
    )
}

private fun PointConfig.toMap(): Point {
    return Point(
        latitude = Latitude(latitude.value),
        longitude = Longitude(longitude.value)
    )
}
