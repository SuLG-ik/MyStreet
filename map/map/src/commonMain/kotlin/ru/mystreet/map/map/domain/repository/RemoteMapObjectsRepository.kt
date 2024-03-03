package ru.mystreet.map.map.domain.repository

import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.entity.MapFrame

interface RemoteMapObjectsRepository {

    suspend fun getFramedMapObjects(frames: MapFrame): FramedMapObjects

}