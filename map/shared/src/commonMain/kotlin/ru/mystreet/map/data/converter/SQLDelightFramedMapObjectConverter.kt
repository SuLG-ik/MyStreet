package ru.mystreet.map.data.converter

import ru.mystreet.errors.domain.exception.UnknownCategoryException
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.MapObjectPart
import ru.mystreet.map.domain.entity.SavedMapFrame
import ru.sulgik.mapkit.geometry.Point

class SQLDelightFramedMapObjectConverter {

    fun ru.mystreet.core.db.MapObjectPart.convert(): MapObjectPart {
        return MapObjectPart(
            id = id,
            title = title,
            point = Point(latitude = latitude, longitude = longitude),
            category = MapObjectCategory.fromId(category)
                ?: throw UnknownCategoryException(category),
            area = null,
        )
    }

    fun ru.mystreet.core.db.MapFrame.convert(): SavedMapFrame {
        return SavedMapFrame(
            column = column.toInt(),
            row = row.toInt(),
            updatedAt = updatedAt,
        )
    }

}