package ru.mystreet.map

import com.yandex.mapkit.map.ClusterizedPlacemarkCollection
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject

actual class MapObjects(
    private val mapObjects: MapObjectCollection,
) {

    private val tapListeners =
        mutableMapOf<MapObjectTapListener, com.yandex.mapkit.map.MapObjectTapListener>()


    actual fun addPlacemark(): Placemark {
        return mapObjects.addPlacemark().toCommon()
    }

    actual fun addClusterizedPlacemark(clusterListener: ClusterListener): ClusterizedPlacemark {
        return mapObjects.addClusterizedPlacemarkCollection(
            MappingClusterListener(
                clusterListener = clusterListener
            )
        ).toCommon()
    }

    actual fun removePlacemarks(clusterizedPlacemark: ClusterizedPlacemark) {
        mapObjects.remove(clusterizedPlacemark.nativeClusterizedPlacemark)
    }

    actual fun addTapListener(listener: MapObjectTapListener) {
        val nativeListener = MappingMapObjectTapListener(listener)
        tapListeners[listener] = nativeListener
        mapObjects.addTapListener(nativeListener)
    }

    actual fun removeTapListener(listener: MapObjectTapListener) {
        return mapObjects.removeTapListener(tapListeners[listener] ?: return)
    }

    actual fun addMapObjects(): MapObjects {
        return mapObjects.addCollection().toCommon()
    }

    actual fun visit(visitor: MapObjectVisitor) {
        return mapObjects.traverse(visitor)
    }

}

internal fun MapObjectCollection.toCommon(): MapObjects {
    return MapObjects(this)
}

internal fun ClusterizedPlacemarkCollection.toCommon(): ClusterizedPlacemark {
    return ClusterizedPlacemark(this)
}

fun PlacemarkMapObject.toCommon(): Placemark {
    return Placemark(this)
}