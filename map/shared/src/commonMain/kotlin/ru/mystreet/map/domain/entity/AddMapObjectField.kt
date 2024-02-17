package ru.mystreet.map.domain.entity

import kotlinx.serialization.Serializable
import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude
import ru.mystreet.map.geomety.Point

@Serializable
data class AddMapObjectField(
    val title: String,
    val description: String,
    val point: Point,
    val category: MapObjectCategory,
)