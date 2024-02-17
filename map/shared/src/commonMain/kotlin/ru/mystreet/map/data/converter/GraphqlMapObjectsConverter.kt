package ru.mystreet.map.data.converter

import ru.mystreet.map.data.model.GetAllMapObjectsQuery
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.MapObjectTag
import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude

class GraphqlMapObjectsConverter {

    fun convert(mapObjects: List<GetAllMapObjectsQuery.GetMapObject>): List<MapObject> {
        return mapObjects.map {
            MapObject(
                id = it.id.toInt(),
                title = it.title,
                latitude = Latitude(it.latitude),
                longitude = Longitude(it.longitude),
                category = it.category.id.convertToCategory(),
                tags = it.tags.map { tag ->
                    MapObjectTag(tag.id.toLong(), tag.title)
                }
            )
        }
    }

}

private fun String.convertToCategory(): MapObjectCategory {
    return MapObjectCategory.fromId(this)
}
