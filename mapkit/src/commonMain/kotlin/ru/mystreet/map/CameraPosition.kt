package ru.mystreet.map

import kotlinx.serialization.Serializable
import ru.mystreet.map.geomety.Point

@Serializable
data class CameraPosition(
    val target: Point,
    val zoom: Float,
    val azimuth: Float,
    val tilt: Float,
)