package ru.mystreet.map

import ru.mystreet.map.geomety.Point

fun interface MapObjectTapListener {

    fun onTap(
        mapObject: BaseMapObject,
        point: Point,
    ): Boolean

}