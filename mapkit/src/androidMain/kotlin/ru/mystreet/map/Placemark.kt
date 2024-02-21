package ru.mystreet.map

import android.content.Context
import com.yandex.mapkit.map.PlacemarkMapObject
import dev.icerock.moko.resources.ImageResource
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.location.toImageProvider

actual class Placemark(
    private val placemark: PlacemarkMapObject,
    private val context: Context,
) {

    actual var geomety: Point
        get() = placemark.geometry.toData()
        set(value) {
            placemark.geometry = value.toNative()
        }

    actual fun setIcon(icon: ImageResource) {
        placemark.setIcon(icon.toImageProvider(context))
    }

} 