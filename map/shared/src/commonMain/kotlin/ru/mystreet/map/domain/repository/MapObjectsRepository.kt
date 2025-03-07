package ru.mystreet.map.domain.repository

import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.sulgik.mapkit.geometry.Latitude
import ru.sulgik.mapkit.geometry.Longitude

interface MapObjectsRepository {

    suspend fun getMapObjectById(id: Long): MapObject

    suspend fun setMapObjectFavourite(id: Long, isFavourite: Boolean)

    suspend fun addMapObject(
        title: String,
        description: String,
        category: MapObjectCategory,
        latitude: Latitude,
        longitude: Longitude,
        tags: List<String>,
    ): MapObject

    suspend fun editMapObject(
        id: Long,
        title: String,
        description: String,
        category: MapObjectCategory,
        tags: List<String>,
    )

    suspend fun uploadMapObjectImages(mapObjectId: Long, images: List<ByteArray>)
    suspend fun deleteMapObject(id: Long)
}