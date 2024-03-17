package ru.mystreet.map

import com.yandex.mapkit.map.CircleMapObject
import com.yandex.mapkit.map.ClusterizedPlacemarkCollection
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectVisitor
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolygonMapObject
import com.yandex.mapkit.map.PolylineMapObject

actual abstract class MapObjectVisitor actual constructor() : MapObjectVisitor {

    override fun onPlacemarkVisited(p0: PlacemarkMapObject) {
        onPlacemarkVisited(p0.toCommon())
    }

    override fun onPolylineVisited(p0: PolylineMapObject) {
    }

    override fun onPolygonVisited(p0: PolygonMapObject) {
    }

    override fun onCircleVisited(p0: CircleMapObject) {
    }

    override fun onCollectionVisitStart(p0: MapObjectCollection): Boolean {
        return true
    }

    override fun onCollectionVisitEnd(p0: MapObjectCollection) {
    }

    override fun onClusterizedCollectionVisitStart(p0: ClusterizedPlacemarkCollection): Boolean {
        return onClusterizedPlacemarkVisitStart(p0.toCommon())
    }

    override fun onClusterizedCollectionVisitEnd(p0: ClusterizedPlacemarkCollection) {
        onClusterizedPlacemarkVisitEnd(p0.toCommon())
    }

    actual abstract fun onPlacemarkVisited(placemark: Placemark)

    actual abstract fun onClusterizedPlacemarkVisitStart(clusterizedPlacemark: ClusterizedPlacemark): Boolean

    actual abstract fun onClusterizedPlacemarkVisitEnd(clusterizedPlacemark: ClusterizedPlacemark)

}