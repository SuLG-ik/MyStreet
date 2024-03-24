package ru.mystreet.map

import cocoapods.YandexMapsMobile.YMKPlacemarkMapObject
import dev.icerock.moko.resources.ImageResource
import kotlinx.cinterop.ExperimentalForeignApi
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.image.ImageProvider

@OptIn(ExperimentalForeignApi::class)
actual class Placemark(
    private val placemark: YMKPlacemarkMapObject,
) {
    actual var geomety: Point
        get() = placemark.geometry.toData()
        set(value) {
            placemark.geometry = value.toNative()
        }

    actual var data: Any? get() = placemark.userData

    actual fun setIcon(icon: ImageProvider) {
        placemark.setIconWithImage(icon.toNative()!!)
    }
    fun setIconStyle(style: IconStyle) {
        TODO()
    }
}