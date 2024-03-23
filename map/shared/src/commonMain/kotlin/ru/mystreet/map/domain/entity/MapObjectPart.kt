package ru.mystreet.map.domain.entity

import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.geomety.Point

data class MapObjectPart(
    val id: Long,
    val title: String,
    val point: Point,
    val category: MapObjectCategory,
)