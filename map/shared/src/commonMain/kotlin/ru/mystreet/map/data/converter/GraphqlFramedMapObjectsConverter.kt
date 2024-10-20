package ru.mystreet.map.data.converter

import ru.mystreet.core.graphql.type.MapObjectsFrameInput
import ru.mystreet.errors.domain.exception.UnknownCategoryException
import ru.mystreet.map.data.model.GetFramedMapObjectsPartQuery
import ru.mystreet.map.data.model.fragment.LinearRingFragment
import ru.mystreet.map.data.model.fragment.PointFragment
import ru.mystreet.map.data.model.fragment.PolygonFragment
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapArea
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.MapObjectPart
import ru.sulgik.mapkit.geometry.Latitude
import ru.sulgik.mapkit.geometry.LinearRing
import ru.sulgik.mapkit.geometry.Longitude
import ru.sulgik.mapkit.geometry.Point
import ru.sulgik.mapkit.geometry.Polygon

class GraphqlFramedMapObjectsConverter {

    fun GetFramedMapObjectsPartQuery.Framed.convert(): FramedMapObjects {
        return FramedMapObjects(
            frame = frame.mapFrame.convert(),
            objects = objects.map
            {
                it.mapObjectPart.convert()
            }
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
                ?: throw UnknownCategoryException(category.id),
            area = area?.convert()
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

private fun ru.mystreet.map.data.model.fragment.MapObjectPart.Area.convert(): MapArea {
    return MapArea(
        polygon.polygonFragment.convert()
    )
}

private fun PolygonFragment.convert(): Polygon {
    return Polygon(
        innerRing = innerRings.map { it.linearRingFragment.convert() },
        outerRing = outerRing.linearRingFragment.convert(),
    )
}

private fun LinearRingFragment.convert(): LinearRing {
    return LinearRing(
        points = points.map { it.pointFragment.convert() }
    )
}

private fun PointFragment.convert(): Point {
    return Point(
        latitude = Latitude(latitude),
        longitude = Longitude(longitude),
    )
}


