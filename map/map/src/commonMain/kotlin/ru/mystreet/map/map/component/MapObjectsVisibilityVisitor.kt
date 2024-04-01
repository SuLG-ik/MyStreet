package ru.mystreet.map.map.component

import ru.mystreet.map.ClusterizedPlacemark
import ru.mystreet.map.IconStyle
import ru.mystreet.map.MapObjectVisitor
import ru.mystreet.map.Placemark
import ru.mystreet.map.domain.entity.MapGeoObject

class MapObjectsVisibilityVisitor(
    private val isVisible: (MapGeoObject.MapObject) -> Boolean,
    private val isSelected: (MapGeoObject.MapObject) -> Boolean,
) : MapObjectVisitor() {

    override fun onPlacemarkVisited(placemark: Placemark) {
        val data = placemark.data as? MapGeoObject.MapObject ?: return
        val scale = if (isSelected(data)) 1.5f else 1f
        placemark.setIconStyle(IconStyle(isVisible = isVisible(data), scale = scale))
    }

    override fun onClusterizedPlacemarkVisitStart(clusterizedPlacemark: ClusterizedPlacemark): Boolean {
        return true
    }

    override fun onClusterizedPlacemarkVisitEnd(clusterizedPlacemark: ClusterizedPlacemark) {
    }

}