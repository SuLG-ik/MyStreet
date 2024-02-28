package ru.mystreet.map.map.component

import ru.mystreet.app.MapController
import ru.mystreet.map.domain.entity.MapObjectCategory

interface Map {

    val mapController: MapController

    fun setCategories(categories: List<MapObjectCategory>)

    fun onFollowLocation()

    fun onZoomOutPress(isStart: Boolean)

    fun onZoomInPress(isStart: Boolean)


}