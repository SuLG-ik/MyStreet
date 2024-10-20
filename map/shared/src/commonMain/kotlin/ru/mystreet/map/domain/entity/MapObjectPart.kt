package ru.mystreet.map.domain.entity

import ru.sulgik.mapkit.geometry.Point

data class MapObjectPart(
    val id: Long,
    val title: String,
    val point: Point,
    val category: MapObjectCategory,
    val area: MapArea?,
)