package ru.mystreet.map

import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolygonMapObject
import com.yandex.mapkit.map.MapObjectTapListener as NativeMapObjectTapListener

class MappingMapObjectTapListener(
    private val listener: MapObjectTapListener,
) : NativeMapObjectTapListener {
    override fun onMapObjectTap(p0: MapObject, p1: com.yandex.mapkit.geometry.Point): Boolean {
        return listener.onTap(p0.toCommon(), p1.toCommon())
    }
}

internal fun MapObject.toCommon(): BaseMapObject {
    return when (this) {
        is PlacemarkMapObject -> this.toCommon()
        is PolygonMapObject -> this.toCommon()
        else -> UnknownBaseMapObject(this)
    }
}
