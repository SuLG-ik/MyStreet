package ru.mystreet.map.domain.entity

sealed interface MapGeoObject {

    data class MapObject(val id: Long, val category: MapObjectCategory) : MapGeoObject

}