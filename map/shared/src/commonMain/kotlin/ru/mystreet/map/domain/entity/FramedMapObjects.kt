package ru.mystreet.map.domain.entity

data class FramedMapObjects(
    val frame: MapFrame,
    val objects: List<MapObjectPart>,
)