package ru.mystreet.map

import ru.mystreet.map.geomety.Point

data class CameraPosition(
    val target: Point,
    val zoom: Float,
    val azimuth: Float,
    val tilt: Float,
)