package ru.mystreet.map.domain.entity

import kotlinx.serialization.Serializable
import ru.mystreet.map.CameraPosition

@Serializable
data class MapConfig(
    val initialCameraPosition: CameraPosition?,
)