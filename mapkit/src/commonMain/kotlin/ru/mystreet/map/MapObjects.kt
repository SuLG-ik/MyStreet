package ru.mystreet.map

expect class MapObjects {

    fun addPlacemark(): Placemark

    fun addClusterizedPlacemark(): ClusterizedPlacemark

}