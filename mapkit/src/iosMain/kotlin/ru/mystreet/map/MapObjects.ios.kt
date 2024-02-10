package ru.mystreet.map

import cocoapods.YandexMapsMobile.YMKMapObjectCollection
import cocoapods.YandexMapsMobile.YMKPlacemarkMapObject
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class MapObjects(
    private val mapObjectCollection: YMKMapObjectCollection,
) {

    actual fun addPlacemark(): Placemark {
        return mapObjectCollection.addPlacemark().toData()
    }

}


@ExperimentalForeignApi
private fun YMKPlacemarkMapObject.toData(): Placemark {
    return Placemark(this)
}