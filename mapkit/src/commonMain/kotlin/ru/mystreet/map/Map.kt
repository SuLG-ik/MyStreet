package ru.mystreet.map


expect class Map {

    val cameraPosition: CameraPosition

    fun move(cameraPosition: CameraPosition, animation: MapAnimation? = null, listener: CameraCallback? = null)


}