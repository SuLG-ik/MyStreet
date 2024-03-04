package ru.mystreet.map

import ru.mystreet.map.geomety.Point
import ru.mystreet.map.geomety.ScreenPoint

expect class MapWindow {

    val width: Int

    val height: Int

    val map: Map

    fun screenToWorld(point: ScreenPoint): Point?

    fun addSizeChangedListener(listener: SizeChangedListener)

    fun setUserLocationObjectsListener(userLocationObjectListener: UserLocationObjectListener)

}