package ru.mystreet.map

import cocoapods.YandexMapsMobile.YMKCluster
import cocoapods.YandexMapsMobile.YMKClusterListenerProtocol
import cocoapods.YandexMapsMobile.YMKClusterizedPlacemarkCollection
import cocoapods.YandexMapsMobile.YMKMapObjectCollection
import cocoapods.YandexMapsMobile.YMKPlacemarkMapObject
import kotlinx.cinterop.ExperimentalForeignApi
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
class ClusterListener: NSObject(), YMKClusterListenerProtocol {
    override fun onClusterAddedWithCluster(cluster: YMKCluster) {

    }

}

@OptIn(ExperimentalForeignApi::class)
actual class MapObjects(
    private val mapObjectCollection: YMKMapObjectCollection,
) {

    actual fun addPlacemark(): Placemark {
        return mapObjectCollection.addPlacemark().toData()
    }

    actual fun addClusterizedPlacemark(): ClusterizedPlacemark {
        return mapObjectCollection.addClusterizedPlacemarkCollectionWithClusterListener(ClusterListener()).toNative()
    }

}

@OptIn(ExperimentalForeignApi::class)
private fun YMKClusterizedPlacemarkCollection.toNative(): ClusterizedPlacemark {
    return ClusterizedPlacemark(this)
}


@ExperimentalForeignApi
private fun YMKPlacemarkMapObject.toData(): Placemark {
    return Placemark(this)
}