package ru.mystreet.map

import dev.icerock.moko.resources.ImageResource
import ru.mystreet.map.geomety.Point

expect class ClusterizedPlacemark {

    fun addPlacemarks(points: List<Point>, icon: ImageResource, iconStyle: IconStyle)

}