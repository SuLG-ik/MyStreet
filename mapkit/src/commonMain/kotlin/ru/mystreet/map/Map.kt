package ru.mystreet.map

import ru.mystreet.map.geomety.VisibleArea

expect class Map {

    val cameraPosition: CameraPosition

    val mapObjects: MapObjects

    fun move(
        cameraPosition: CameraPosition,
        animation: MapAnimation? = null,
        listener: CameraCallback? = null
    )

    fun addCameraListener(listener: CameraListener)

    fun removeCameraListener(listener: CameraListener)

    fun visibleArea(cameraPosition: CameraPosition): VisibleArea

}