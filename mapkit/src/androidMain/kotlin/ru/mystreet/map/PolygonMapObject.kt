package ru.mystreet.map

import com.yandex.mapkit.map.PolygonMapObject

actual class PolygonMapObject(
    val nativePolygon: PolygonMapObject,
) : BaseMapObject {

    var geometry: Polygon
        get() = nativePolygon.geometry.toCommon()
        set(value) {
            nativePolygon.geometry = value.toNative()
        }
    actual override var data: Any?
        get() = nativePolygon.userData
        set(value) {
            nativePolygon.userData = value
        }

    override var zIndex: Float
        get() = nativePolygon.zIndex
        set(value) {
            nativePolygon.zIndex = value
        }

    override var isVisible: Boolean
        get() = nativePolygon.isVisible
        set(value) {
            nativePolygon.isVisible = value
        }

}

fun Polygon.toNative(): com.yandex.mapkit.geometry.Polygon {
    return com.yandex.mapkit.geometry.Polygon(
        outerRing.toNative(),
        innerRings.map { it.toNative() }
    )
}

private fun LinearRing.toNative(): com.yandex.mapkit.geometry.LinearRing {
    return com.yandex.mapkit.geometry.LinearRing(points.map { it.toNative() })
}

private fun com.yandex.mapkit.geometry.Polygon.toCommon(): Polygon {
    return Polygon(
        outerRing = outerRing.toCommon(),
        innerRings = innerRings.map { it.toCommon() }
    )
}

private fun com.yandex.mapkit.geometry.LinearRing.toCommon(): LinearRing {
    return LinearRing(
        points = points.map { it.toCommon() }
    )
}

fun PolygonMapObject.toCommon(): ru.mystreet.map.PolygonMapObject {
    return ru.mystreet.map.PolygonMapObject(this)
}
