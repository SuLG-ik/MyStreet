package ru.mystreet.map.data.converter

import ru.mystreet.map.data.model.GetAllMapObjectsQuery
import ru.mystreet.map.domain.entity.MapObject

class GraphqlMapObjectsConverter {

    fun convert(mapObjects: List<GetAllMapObjectsQuery.GetMapObject>): List<MapObject> {
        return mapObjects.map {
            MapObject(
                it.id.toInt(),
                it.title,
                it.latitude,
                it.longitude,
            )
        }
    }

}