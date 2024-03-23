package ru.mystreet.map.domain.repository

import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame

interface RemoteMapObjectsRepository {

    suspend fun getFramedMapObjects(frames: MapFrame): FramedMapObjects

}