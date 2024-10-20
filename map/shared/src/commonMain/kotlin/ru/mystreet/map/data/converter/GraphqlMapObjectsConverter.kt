package ru.mystreet.map.data.converter

import ru.mystreet.errors.domain.exception.UnknownCategoryException
import ru.mystreet.map.data.model.fragment.MapObjectFull
import ru.mystreet.map.data.model.fragment.MapObjectReviewFull
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.MapObjectReview
import ru.mystreet.map.domain.entity.MapObjectTag
import ru.mystreet.map.domain.entity.ReviewAuthor
import ru.mystreet.map.domain.entity.UserMapObject
import ru.sulgik.mapkit.geometry.Latitude
import ru.sulgik.mapkit.geometry.Longitude
import ru.sulgik.mapkit.geometry.Point

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
            category = MapObjectCategory.fromId(category.id) ?: throw UnknownCategoryException(category.id),
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