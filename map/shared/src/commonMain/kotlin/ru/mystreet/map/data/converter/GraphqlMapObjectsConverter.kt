package ru.mystreet.map.data.converter

import ru.mystreet.map.data.model.fragment.MapObjectFull
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.MapObjectTag
import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude

class GraphqlMapObjectsConverter {

    fun convert(mapObject: MapObjectFull): MapObject {
        return MapObject(
            id = mapObject.id.toLong(),
            title = mapObject.title,
            description = mapObject.description,
            latitude = Latitude(mapObject.latitude),
            longitude = Longitude(mapObject.longitude),
            category = mapObject.category.id.convertToCategory(),
            tags = mapObject.tags.map { tag ->
                MapObjectTag(tag.id.toLong(), tag.title)
            },
            images = mapObject.images.map { it.path },
        )
    }

}

private fun String.convertToCategory(): MapObjectCategory {
    return MapObjectCategory.fromId(this)
}
