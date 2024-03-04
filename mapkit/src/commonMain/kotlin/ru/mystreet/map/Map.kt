package ru.mystreet.map

import ru.mystreet.map.geomety.VisibleArea

fun RGBA(r: Int, g: Int, b: Int, a: Int): Int {
    return a shl 24 or (r and 255 shl 16) or (g and 255 shl 8) or (b and 255)
}


expect class Map {

    val cameraPosition: CameraPosition

    val mapObjects: MapObjects

    fun move(
        cameraPosition: CameraPosition,
        animation: MapAnimation? = null,
        listener: CameraCallback? = null,
    )

    fun addCameraListener(listener: CameraListener)

    fun removeCameraListener(listener: CameraListener)

    fun visibleArea(cameraPosition: CameraPosition): VisibleArea

}