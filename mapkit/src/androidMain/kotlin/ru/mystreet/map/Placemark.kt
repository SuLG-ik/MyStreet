package ru.mystreet.map

import com.yandex.mapkit.map.PlacemarkMapObject
import ru.mystreet.map.geomety.Point

actual class Placemark(
    private val placemark: PlacemarkMapObject,
) {
    actual var geomety: Point
        get() = placemark.geometry.toData()
        set(value) {
            placemark.geometry = value.toNative()
        }
} 