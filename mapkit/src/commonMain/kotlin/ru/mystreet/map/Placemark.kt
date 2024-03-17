package ru.mystreet.map

import ru.mystreet.map.geomety.Point
import ru.mystreet.map.image.ImageProvider

expect class Placemark : BaseMapObject {

    var geomety: Point

    override var data: Any?
    fun setIcon(icon: ImageProvider)
    fun setIconStyle(style: IconStyle)

}