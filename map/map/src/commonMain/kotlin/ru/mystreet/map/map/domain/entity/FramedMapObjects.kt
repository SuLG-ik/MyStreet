package ru.mystreet.map.map.domain.entity

data class FramedMapObjects(
    val frame: MapFrame,
    val objects: List<MapObjectPart>,
)