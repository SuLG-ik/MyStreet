package ru.mystreet.map

expect abstract class MapObjectVisitor() {

    abstract fun onPlacemarkVisited(placemark: Placemark)

    abstract fun onClusterizedPlacemarkVisitStart(clusterizedPlacemark: ClusterizedPlacemark): Boolean

    abstract fun onClusterizedPlacemarkVisitEnd(clusterizedPlacemark: ClusterizedPlacemark)

}