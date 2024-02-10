package ru.mystreet.map

expect class Map {

    val cameraPosition: CameraPosition

    val mapObjects: MapObjects

    fun move(
        cameraPosition: CameraPosition,
        animation: MapAnimation? = null,
        listener: CameraCallback? = null
    )

    fun addCameraListener(listener: CameraListener)

}