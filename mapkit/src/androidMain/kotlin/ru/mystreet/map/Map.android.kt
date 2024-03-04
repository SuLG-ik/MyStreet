package ru.mystreet.map

import android.content.Context
import com.yandex.mapkit.Animation
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.VisibleRegion
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import dev.icerock.moko.resources.ImageResource
import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.geomety.VisibleArea
import ru.mystreet.map.location.toImageProvider
import com.yandex.mapkit.map.CameraListener as NativeCameraListener


fun RGBA(r: Int, g: Int, b: Int, a: Int): Int {
    return a shl 24 or (r and 255 shl 16) or (g and 255 shl 8) or (b and 255)
}

class UserLocationImage(
    private val image: ImageResource,
    private val context: Context,
) : UserLocationObjectListener {
    override fun onObjectAdded(p0: UserLocationView) {
        p0.arrow.setIcon(image.toImageProvider(context))
        p0.pin.setIcon(image.toImageProvider(context))
        p0.accuracyCircle.fillColor = RGBA(108, 176, 244, 50)
    }

    override fun onObjectRemoved(p0: UserLocationView) {
    }

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {
        p0.arrow.setIcon(image.toImageProvider(context))
        p0.pin.setIcon(image.toImageProvider(context))
        p0.accuracyCircle.fillColor = RGBA(108, 176, 244, 50)
    }

}


actual class Map(
    private val map: Map,
) {

    private val cameraListeners = mutableMapOf<CameraListener, NativeCameraListener>()

    actual fun move(
        cameraPosition: CameraPosition,
        animation: MapAnimation?,
        listener: CameraCallback?,
    ) {
        if (animation == null) {
            map.move(cameraPosition.toNative())
            listener?.onMoveFinished(true)
        } else
            map.move(cameraPosition.toNative(), animation.toCommon(), listener?.toCommon())
    }


    actual val cameraPosition: CameraPosition
        get() = map.cameraPosition.toCommon()

    actual val mapObjects: MapObjects = map.mapObjects.toCommon()

    actual fun addCameraListener(listener: CameraListener) {
        val nativeListener = MappingCameraListener(this, listener)
        cameraListeners[listener] = nativeListener
        map.addCameraListener(nativeListener)
    }

    actual fun removeCameraListener(listener: CameraListener) {
        val nativeListener = cameraListeners.remove(listener) ?: return
        map.addCameraListener(nativeListener)
    }

    actual fun visibleArea(cameraPosition: CameraPosition): VisibleArea {
        return map.visibleRegion(cameraPosition.toNative()).toCommon()
    }

}

fun com.yandex.mapkit.map.CameraPosition.toCommon(): CameraPosition {
    return CameraPosition(target.toCommon(), zoom, azimuth, tilt)
}

fun com.yandex.mapkit.geometry.Point.toCommon(): Point {
    return Point(Latitude(latitude), Longitude(longitude))
}

fun VisibleRegion.toCommon(): VisibleArea {
    return VisibleArea(
        topLeft = topLeft.toCommon(),
        topRight = topRight.toCommon(),
        bottomLeft = bottomLeft.toCommon(),
        bottomRight = bottomRight.toCommon()
    )
}

class DelegatingCameraCallback(
    private val callback: CameraCallback,
) : Map.CameraCallback {
    override fun onMoveFinished(isCompleted: Boolean) {
        callback.onMoveFinished(isCompleted)
    }
}


private fun CameraCallback.toCommon(): Map.CameraCallback {
    return DelegatingCameraCallback(this)
}

private fun MapAnimation.toCommon(): Animation {
    return Animation(type.toCommon(), duration)
}

private fun MapAnimation.Type.toCommon(): Animation.Type {
    return when (this) {
        MapAnimation.Type.SMOOTH -> Animation.Type.SMOOTH
        MapAnimation.Type.LINEAR -> Animation.Type.LINEAR
    }
}

private fun CameraPosition.toNative(): com.yandex.mapkit.map.CameraPosition {
    return com.yandex.mapkit.map.CameraPosition(target.toNative(), zoom, azimuth, tilt)
}

fun Point.toNative(): com.yandex.mapkit.geometry.Point {
    return com.yandex.mapkit.geometry.Point(latitude.value, longitude.value)
}
