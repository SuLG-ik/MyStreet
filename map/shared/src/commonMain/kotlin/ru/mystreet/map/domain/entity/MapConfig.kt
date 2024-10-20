package ru.mystreet.map.domain.entity

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
data class MapConfig(
    val initialCameraPosition: CameraPositionConfig?,
)

@Serializable
data class CameraPositionConfig(
    val target: PointConfig,
    val zoom: Float,
    val azimuth: Float,
    val tilt: Float,
)
@Serializable
data class PointConfig(val latitude: LatitudeConfig, val longitude: LongitudeConfig)

@Serializable
@JvmInline
value class LatitudeConfig(val value: Double)

@Serializable
@JvmInline
value class LongitudeConfig(val value: Double)