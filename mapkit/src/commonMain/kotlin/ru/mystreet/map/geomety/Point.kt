package ru.mystreet.map.geomety

import kotlinx.serialization.Serializable

@Serializable
data class Point(
    val latitude: Latitude = Latitude(0.0),
    val longitude: Longitude = Longitude(0.0),
)