package ru.mystreet.map

import com.yandex.mapkit.map.CircleMapObject
import ru.mystreet.map.geomety.Point

actual class CircleMapObject(
    private val nativeCircleMapObject: CircleMapObject,
) {
    actual var fillColor: Int
        get() = nativeCircleMapObject.fillColor
        set(value) {
            nativeCircleMapObject.fillColor = value
        }

}