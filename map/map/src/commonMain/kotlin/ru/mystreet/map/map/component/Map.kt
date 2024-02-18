package ru.mystreet.map.map.component

import ru.mystreet.app.MapController

interface Map {

    val mapController: MapController

    fun onFollowLocation()

    fun onZoomOutPress(isStart: Boolean)

    fun onZoomInPress(isStart: Boolean)


}