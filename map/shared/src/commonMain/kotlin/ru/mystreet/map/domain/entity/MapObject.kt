package ru.mystreet.map.domain.entity

data class MapObject(
    val id: Int,
    val title: String,
    val latitude: Double,
    val longitude: Double,
)