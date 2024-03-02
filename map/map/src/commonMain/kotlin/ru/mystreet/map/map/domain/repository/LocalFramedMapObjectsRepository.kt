package ru.mystreet.map.map.domain.repository

import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.entity.MapFrame

interface LocalFramedMapObjectsRepository {

    suspend fun saveMapObjects(objects: FramedMapObjects)

    suspend fun getMapObjects(frame: MapFrame): FramedMapObjects?

}