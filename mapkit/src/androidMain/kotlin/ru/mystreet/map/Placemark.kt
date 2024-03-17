package ru.mystreet.map

import com.yandex.mapkit.map.PlacemarkMapObject
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.image.ImageProvider

actual class Placemark(
    val nativePlacemark: PlacemarkMapObject,
) : BaseMapObject {

    actual var geomety: Point
        get() = nativePlacemark.geometry.toCommon()
        set(value) {
            nativePlacemark.geometry = value.toNative()
        }

    actual fun setIcon(icon: ImageProvider) {
        nativePlacemark.setIcon(icon.toNative())
    }

    actual override var data: Any?
        get() = nativePlacemark.userData
        set(value) {
            nativePlacemark.userData = value
        }

    override var zIndex: Float
        get() = nativePlacemark.zIndex
        set(value) {
            nativePlacemark.zIndex = value
        }

    override var isVisible: Boolean
        get() = nativePlacemark.isVisible
        set(value) {
            nativePlacemark.isVisible = value
        }

    actual fun setIconStyle(style: IconStyle) {
        nativePlacemark.setIconStyle(style.toNative())
    }

} 