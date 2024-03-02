package ru.mystreet.map.map.data.converter

import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.map.domain.entity.MapObjectPart
import ru.mystreet.map.map.domain.entity.SavedMapFrame


class SQLDelightFramedMapObjectConverter {

    fun ru.mystreet.core.db.MapObjectPart.convert(): MapObjectPart {
        return MapObjectPart(
            id = id,
            title = title,
            point = Point(latitude = Latitude(latitude), longitude = Longitude(longitude)),
            category = MapObjectCategory.fromId(category),
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