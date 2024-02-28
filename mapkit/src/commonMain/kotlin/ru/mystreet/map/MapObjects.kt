package ru.mystreet.map

expect class MapObjects {

    fun addPlacemark(): Placemark

    fun addClusterizedPlacemark(clusterListener: ClusterListener): ClusterizedPlacemark

    fun removeTapListener(listener: MapObjectTapListener)

    fun addTapListener(listener: MapObjectTapListener)

    fun removePlacemarks(clusterizedPlacemark: ClusterizedPlacemark)

}