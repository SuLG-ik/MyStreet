package ru.mystreet.map

import android.graphics.PointF
import com.yandex.mapkit.MapKitFactory
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.geomety.ScreenPoint
import com.yandex.mapkit.ScreenPoint as NativeScreenPoint
import com.yandex.mapkit.map.MapWindow as NativeMapWindow
import com.yandex.mapkit.map.SizeChangedListener as NativeSizeChangedListener

actual class MapWindow(
    private val nativeMapWindow: NativeMapWindow,
) {

    private val sizeChangedListeners: MutableMap<SizeChangedListener, NativeSizeChangedListener> =
        mutableMapOf()

    actual val map: Map = Map(nativeMapWindow.map)

    actual val width: Int
        get() = nativeMapWindow.width()

    actual val height: Int
        get() = nativeMapWindow.height()

    actual fun screenToWorld(point: ScreenPoint): Point? {
        return nativeMapWindow.screenToWorld(point.toNative())?.toCommon()
    }

    actual fun addSizeChangedListener(listener: SizeChangedListener) {
        val nativeListener = MappingSizeChangedListener(this, listener)
        sizeChangedListeners[listener] = nativeListener
        nativeMapWindow.addSizeChangedListener(nativeListener)
    }

    private val userLocation =
        MapKitFactory.getInstance().createUserLocationLayer(nativeMapWindow).apply {
            isAutoZoomEnabled = true
            isVisible = true
        }

    actual fun setUserLocationObjectsListener(userLocationObjectListener: UserLocationObjectListener) {
        userLocation.setObjectListener(userLocationObjectListener)
    }

}

private fun ScreenPoint.toNative(): NativeScreenPoint {
    return NativeScreenPoint(x, y)
}
