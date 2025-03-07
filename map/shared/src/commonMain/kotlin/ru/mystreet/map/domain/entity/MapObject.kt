package ru.mystreet.map.domain.entity

import ru.sulgik.mapkit.geometry.Point

data class MapObject(
    val id: Long,
    val title: String,
    val description: String,
    val point: Point,
    val category: MapObjectCategory,
    val tags: List<MapObjectTag>,
    val images: List<String>,
    val rating: String?,
    val forUser: UserMapObject?,
)

data class UserMapObject(
    val isFavourite: Boolean,
)