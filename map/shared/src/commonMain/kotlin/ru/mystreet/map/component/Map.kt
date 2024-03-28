package ru.mystreet.map.component

import kotlinx.coroutines.flow.Flow
import ru.mystreet.app.MapController
import ru.mystreet.map.domain.entity.MapObjectCategory

interface Map {

    val mapController: MapController

    val isNecessaryZoomAlert: Flow<Boolean>

    fun setCategories(categories: List<MapObjectCategory>)

    fun onFollowLocation()

    fun onZoomOutPress(isStart: Boolean)

    fun onZoomInPress(isStart: Boolean)


    fun onLocationPermissionGranted()

    fun setSelected(mapObjectId: Long?)

}