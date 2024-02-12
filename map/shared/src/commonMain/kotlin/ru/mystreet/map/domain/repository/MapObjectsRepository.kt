package ru.mystreet.map.domain.repository

import ru.mystreet.map.MapObjects
import ru.mystreet.map.domain.entity.MapObject

interface MapObjectsRepository {

    suspend fun getAllMapObjects(): List<MapObject>

}