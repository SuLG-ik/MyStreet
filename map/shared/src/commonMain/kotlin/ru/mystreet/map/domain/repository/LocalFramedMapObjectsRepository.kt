package ru.mystreet.map.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.entity.MapObjectPart

interface LocalFramedMapObjectsRepository {

    suspend fun saveMapObjects(objects: FramedMapObjects)

    suspend fun saveMapObject(frame: MapFrame, mapObject: MapObjectPart)

    suspend fun deleteMapObject(mapObjectId: Long)

    suspend fun getMapObjects(frame: MapFrame): FramedMapObjects?

    fun getMapObjectsFlow(frame: MapFrame): Flow<FramedMapObjects>?

}