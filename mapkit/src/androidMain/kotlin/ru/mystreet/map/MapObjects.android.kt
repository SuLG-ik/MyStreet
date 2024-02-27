package ru.mystreet.map

import android.content.Context
import com.yandex.mapkit.map.ClusterizedPlacemarkCollection
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject

actual class MapObjects(
    private val mapObjects: MapObjectCollection,
    private val context: Context,
) {

    private val tapListeners =
        mutableMapOf<MapObjectTapListener, com.yandex.mapkit.map.MapObjectTapListener>()


    actual fun addPlacemark(): Placemark {
        return mapObjects.addPlacemark().toCommon(context)
    }

    actual fun addClusterizedPlacemark(clusterListener: ClusterListener): ClusterizedPlacemark {
        return mapObjects.addClusterizedPlacemarkCollection(
            MappingClusterListener(
                clusterListener = clusterListener,
                context = context
            )
        ).toNative(context)
    }

    actual fun addTapListener(listener: MapObjectTapListener) {
        val nativeListener = MappingMapObjectTapListener(listener, context)
        tapListeners[listener] = nativeListener
        mapObjects.addTapListener(nativeListener)
    }

    actual fun removeTapListener(listener: MapObjectTapListener) {
        return mapObjects.removeTapListener(tapListeners[listener] ?: return)
    }

}

private fun ClusterizedPlacemarkCollection.toNative(
    context: Context
): ClusterizedPlacemark {
    return ClusterizedPlacemark(this, context)
}

fun PlacemarkMapObject.toCommon(
    context: Context
): Placemark {
    return Placemark(this, context)
}