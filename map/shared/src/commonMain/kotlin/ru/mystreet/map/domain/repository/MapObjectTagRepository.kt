package ru.mystreet.map.domain.repository

import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.MapObjectTag

interface MapObjectTagRepository {

   suspend fun getMapObjectsTag(category: MapObjectCategory, query: String): List<MapObjectTag>

}