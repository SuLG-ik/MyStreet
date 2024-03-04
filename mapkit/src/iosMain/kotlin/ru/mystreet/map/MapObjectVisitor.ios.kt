package ru.mystreet.map

actual abstract class MapObjectVisitor actual constructor() {
    actual abstract fun onPlacemarkVisited(placemark: Placemark) {
    }

    actual abstract fun onClusterizedPlacemarkVisitStart(clusterizedPlacemark: ClusterizedPlacemark): Boolean {
        TODO("Not yet implemented")
    }

    actual abstract fun onClusterizedPlacemarkVisitEnd(clusterizedPlacemark: ClusterizedPlacemark) {
    }

}