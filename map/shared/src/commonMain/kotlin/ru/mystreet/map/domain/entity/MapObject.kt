package ru.mystreet.map.domain.entity

import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude

data class MapObject(
    val id: Int,
    val title: String,
    val latitude: Latitude,
    val longitude: Longitude,
    val category: MapObjectCategory,
    val tags: List<MapObjectTag>,
)