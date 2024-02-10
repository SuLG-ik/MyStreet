package ru.mystreet.map

import android.content.Context
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject

actual class MapObjects(
    private val mapObjects: MapObjectCollection,
    private val context: Context,
) {

    actual fun addPlacemark(): Placemark {
        return mapObjects.addPlacemark().toData(context)
    }

}

private fun PlacemarkMapObject.toData(context: Context): Placemark {
    return Placemark(this, context)
}