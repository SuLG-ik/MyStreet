package ru.mystreet.map

import dev.icerock.moko.resources.ImageResource
import ru.mystreet.map.geomety.Point

expect class Placemark : BaseMapObject {

    var geomety: Point

    override var data: Any?

    fun setIcon(icon: ImageResource)

}