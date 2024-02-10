package ru.mystreet.map

import cocoapods.YandexMapsMobile.YMKMapWindow
import cocoapods.YandexMapsMobile.YMKScreenPoint
import kotlinx.cinterop.ExperimentalForeignApi
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.geomety.ScreenPoint

@OptIn(ExperimentalForeignApi::class)
actual class MapWindow(
    private val mapWindow: YMKMapWindow,
) {

    actual val map: Map = Map(mapWindow.map)

    actual val width: Int
        get() = mapWindow.width().toInt()

    actual val height: Int
        get() = mapWindow.height().toInt()

    actual fun screenToWorld(point: ScreenPoint): Point? {
        return mapWindow.screenToWorldWithScreenPoint(point.toNative())?.toData()
    }

    actual fun addSizeChangedListener(listener: SizeChangedListener) {
        mapWindow.addSizeChangedListenerWithSizeChangedListener(MappingSizeChangedListener(this, listener))
    }

}

@OptIn(ExperimentalForeignApi::class)
private fun ScreenPoint.toNative(): YMKScreenPoint {
    return YMKScreenPoint.screenPointWithX(x, y)
}
