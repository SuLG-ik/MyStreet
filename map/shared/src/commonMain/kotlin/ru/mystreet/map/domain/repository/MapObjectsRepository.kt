package ru.mystreet.map.domain.repository

import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude

interface MapObjectsRepository {

    suspend fun getMapObjectById(id: Long): MapObject

    suspend fun addMapObject(
        title: String,
        description: String,
        category: MapObjectCategory,
        latitude: Latitude,
        longitude: Longitude,
        tags: List<String>,
    )

    suspend fun uploadMapObjectImages(mapObjectId: Long, images: List<ByteArray>)
}