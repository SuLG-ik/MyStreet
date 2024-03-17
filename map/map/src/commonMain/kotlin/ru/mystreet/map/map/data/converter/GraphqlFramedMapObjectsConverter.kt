package ru.mystreet.map.map.data.converter

import ru.mystreet.core.graphql.type.MapObjectsFrameInput
import ru.mystreet.map.data.model.GetFramedMapObjectsPartQuery
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.entity.MapFrame
import ru.mystreet.map.map.domain.entity.MapObjectPart

class GraphqlFramedMapObjectsConverter {

    fun GetFramedMapObjectsPartQuery.Framed.convert(): FramedMapObjects {
        return FramedMapObjects(
            frame = frame.mapFrame.convert(),
            objects = objects.map { it.mapObjectPart.convert() }
        )
    }

    private fun ru.mystreet.map.data.model.fragment.MapObjectPart.convert(): MapObjectPart {
        return MapObjectPart(
            id = id.toLong(),
            title = title,
            point = Point(
                latitude = Latitude(point.latitude),
                longitude = Longitude(point.longitude)
            ),
            category = MapObjectCategory.fromId(category.id)
        )
    }

    private fun ru.mystreet.map.data.model.fragment.MapFrame.convert(): MapFrame {
        return MapFrame(column = column, row = row)
    }


    fun List<MapFrame>.convert(): List<MapObjectsFrameInput> {
        return map { it.convert() }
    }

    fun MapFrame.convert(): MapObjectsFrameInput {
        return MapObjectsFrameInput(
            column = column, row = row
        )
    }

}


