package ru.mystreet.map.domain.entity

import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude
import ru.mystreet.map.geomety.Point

data class MapObject(
    val id: Long,
    val title: String,
    val description: String,
    val point: Point,
    val category: MapObjectCategory,
    val tags: List<MapObjectTag>,
    val images: List<String>,
    val forUser: UserMapObject?,
)

data class UserMapObject(
    val isFavourite: Boolean,
)