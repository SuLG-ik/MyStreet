package ru.mystreet.map

import dev.icerock.moko.resources.ImageResource
import ru.mystreet.map.geomety.Point

expect class Placemark {

    var geomety: Point

    fun setIcon(icon: ImageResource)

}