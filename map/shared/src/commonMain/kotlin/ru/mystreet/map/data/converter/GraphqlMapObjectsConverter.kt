package ru.mystreet.map.data.converter

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import ru.mystreet.map.data.model.fragment.MapObjectFull
import ru.mystreet.map.data.model.fragment.MapObjectReviewFull
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.MapObjectReview
import ru.mystreet.map.domain.entity.MapObjectTag
import ru.mystreet.map.domain.entity.ReviewAuthor
import ru.mystreet.map.domain.entity.UserMapObject
import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude
import ru.mystreet.map.geomety.Point

class GraphqlMapObjectsConverter {

    fun MapObjectFull.convert(): MapObject {
        return MapObject(
            id = id.toLong(),
            title = title,
            description = description,
            point = Point(
                latitude = Latitude(point.latitude),
                longitude = Longitude(point.longitude),
            ),
            category = category.id.convertToCategory(),
            tags = tags.map { tag ->
                MapObjectTag(tag.id.toLong(), tag.title)
            },
            images = images.map { it.path },
            forUser = forUser?.convert(),
            rating = score.rating?.formattedValue
        )
    }

    private fun MapObjectFull.ForUser.convert(): UserMapObject {
        return UserMapObject(
            isFavourite = favourite.isFavourite,
        )
    }

    fun MapObjectReviewFull.convert(): MapObjectReview {
        return MapObjectReview(
            id = id.toLong(),
            title = title,
            text = text,
            rating = rating,
            createdDate = createdDate,
            author = author?.name?.let { ReviewAuthor(it) }
        )
    }

}

private fun String.convertToCategory(): MapObjectCategory {
    return MapObjectCategory.fromId(this)
}
