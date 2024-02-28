package ru.mystreet.map.domain.entity

import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude

data class MapObject(
    val id: Long,
    val title: String,
    val description: String,
    val latitude: Latitude,
    val longitude: Longitude,
    val category: MapObjectCategory,
    val tags: List<MapObjectTag>,
    val images: List<String>,
)