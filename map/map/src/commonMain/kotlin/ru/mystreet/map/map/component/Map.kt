package ru.mystreet.map.map.component

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
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
}