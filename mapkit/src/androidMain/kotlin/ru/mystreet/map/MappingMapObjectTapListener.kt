package ru.mystreet.map

import android.content.Context
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.MapObjectTapListener as NativeMapObjectTapListener

class MappingMapObjectTapListener(
    private val listener: MapObjectTapListener,
    private val context: Context,
) : NativeMapObjectTapListener {
    override fun onMapObjectTap(p0: MapObject, p1: com.yandex.mapkit.geometry.Point): Boolean {
        return listener.onTap(p0.toCommon(context), p1.toCommon())
    }
}

private fun MapObject.toCommon(
    context: Context
): BaseMapObject {
    return when (this) {
        is PlacemarkMapObject -> this.toCommon(context)
        else -> UnknownBaseMapObject(this)
    }
}
