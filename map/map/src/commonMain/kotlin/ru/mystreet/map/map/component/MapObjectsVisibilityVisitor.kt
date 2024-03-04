package ru.mystreet.map.map.component

import ru.mystreet.map.ClusterizedPlacemark
import ru.mystreet.map.IconStyle
import ru.mystreet.map.MapObjectVisitor
import ru.mystreet.map.Placemark
import ru.mystreet.map.domain.entity.MapGeoObject
import ru.mystreet.map.map.component.MapObjectsComponent.Companion.CLUSTER_MIN_ZOOM
import ru.mystreet.map.map.component.MapObjectsComponent.Companion.CLUSTER_RADIUS

class MapObjectsVisibilityVisitor(
    private val isVisible: (MapGeoObject.MapObject) -> Boolean,
) : MapObjectVisitor() {

    override fun onPlacemarkVisited(placemark: Placemark) {
        val data = placemark.data as? MapGeoObject.MapObject ?: return
        placemark.setIconStyle(IconStyle(isVisible = isVisible(data)))
    }

    override fun onClusterizedPlacemarkVisitStart(clusterizedPlacemark: ClusterizedPlacemark): Boolean {
        return true
    }

    override fun onClusterizedPlacemarkVisitEnd(clusterizedPlacemark: ClusterizedPlacemark) {
    }

}