package ru.mystreet.map

import android.content.Context
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.geomety.ScreenPoint
import com.yandex.mapkit.ScreenPoint as NativeScreenPoint
import com.yandex.mapkit.map.MapWindow as NativeMapWindow

actual class MapWindow(
    private val nativeMapWindow: NativeMapWindow,
    context: Context,
) {

    actual val map: Map = Map(nativeMapWindow.map, context)

    actual val width: Int
        get() = nativeMapWindow.width()

    actual val height: Int
        get() = nativeMapWindow.height()

    actual fun screenToWorld(point: ScreenPoint): Point? {
        return nativeMapWindow.screenToWorld(point.toNative())?.toData()
    }

    actual fun addSizeChangedListener(listener: SizeChangedListener) {
        nativeMapWindow.addSizeChangedListener(MappingSizeChangedListener(this, listener))
    }

}

private fun ScreenPoint.toNative(): NativeScreenPoint {
    return NativeScreenPoint(x, y)
}
