package ru.mystreet.map.data.converter

import ru.mystreet.map.data.model.GetMapObjectTagsQuery
import ru.mystreet.map.domain.entity.MapObjectTag

class GraphqlMapObjectTagsConverter {

    fun convert(tag: GetMapObjectTagsQuery.Query): MapObjectTag {
        return MapObjectTag(
            tag.id.toLong(),
            tag.title
        )
    }

    fun convert(tags: List<GetMapObjectTagsQuery.Query>): List<MapObjectTag> {
        return tags.map(::convert)
    }


}