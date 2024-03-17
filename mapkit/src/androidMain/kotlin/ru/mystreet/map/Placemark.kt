package ru.mystreet.map

import android.content.Context
import com.yandex.mapkit.map.PlacemarkMapObject
import dev.icerock.moko.resources.ImageResource
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.location.toImageProvider

actual class Placemark(
    private val placemark: PlacemarkMapObject,
    private val context: Context,
) : BaseMapObject {

    actual var geomety: Point
        get() = placemark.geometry.toCommon()
        set(value) {
            placemark.geometry = value.toNative()
        }

    actual fun setIcon(icon: ImageResource) {
        placemark.setIcon(icon.toImageProvider(context))
    }

    actual override var data: Any?
        get() = placemark.userData
        set(value) {
            placemark.userData = value
        }

    override var zIndex: Float
        get() = placemark.zIndex
        set(value) {
            placemark.zIndex = value
        }

} 