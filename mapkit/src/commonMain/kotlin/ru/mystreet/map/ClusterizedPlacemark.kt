package ru.mystreet.map

import ru.mystreet.map.geomety.Point
import ru.mystreet.map.image.ImageProvider

expect class ClusterizedPlacemark {

    fun addPlacemarks(
        points: List<Point>,
        icon: ImageProvider,
        iconStyle: IconStyle = IconStyle()
    ): List<Placemark>

    fun clusterPlacemarks(clusterRadius: Double, minZoom: Int)
    fun visit(visitor: MapObjectVisitor)
    fun remove(placemark: Placemark)

}