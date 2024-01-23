package ru.mystreet.map

import cocoapods.YandexMapsMobile.YMKPlacemarkMapObject
import kotlinx.cinterop.ExperimentalForeignApi
import ru.mystreet.map.geomety.Point

@OptIn(ExperimentalForeignApi::class)
actual class Placemark(
    private val placemark: YMKPlacemarkMapObject
) {
    actual var geomety: Point
        get() = placemark.geometry.toData()
        set(value) { placemark.geometry = value.toNative() }

}