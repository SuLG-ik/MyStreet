package ru.mystreet.map

data class UserLocationView(
    val pin: Placemark,
    val arrow: Placemark,
    val circle: CircleMapObject,
)
