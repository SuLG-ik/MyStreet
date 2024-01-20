package ru.mystreet.map

import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.Map
import ru.mystreet.map.geomety.Point

actual class Map(
    val map: Map,
) {

    actual fun move(
        cameraPosition: CameraPosition,
        animation: MapAnimation?,
        listener: CameraCallback?,
    ) {
        if (animation == null) {
            map.move(cameraPosition.toNative())
            listener?.onMoveFinished(true)
        } else
            map.move(cameraPosition.toNative(), animation.toData(), listener?.toData())
    }


    actual val cameraPosition: CameraPosition
        get() = map.cameraPosition.toData()

}

private fun com.yandex.mapkit.map.CameraPosition.toData(): CameraPosition {
    return CameraPosition(target.toData(), zoom, azimuth, tilt)
}

fun com.yandex.mapkit.geometry.Point.toData(): Point {
    return Point(latitude, longitude)
}

class DelegatingCameraCallback(
    private val callback: CameraCallback,
) : Map.CameraCallback {
    override fun onMoveFinished(isCompleted: Boolean) {
        callback.onMoveFinished(isCompleted)
    }
}


private fun CameraCallback.toData(): Map.CameraCallback {
    return DelegatingCameraCallback(this)
}

private fun MapAnimation.toData(): Animation {
    return Animation(type.toData(), duration)
}

private fun MapAnimation.Type.toData(): Animation.Type {
    return when (this) {
        MapAnimation.Type.SMOOTH -> Animation.Type.SMOOTH
        MapAnimation.Type.LINEAR -> Animation.Type.LINEAR
    }
}

private fun CameraPosition.toNative(): com.yandex.mapkit.map.CameraPosition {
    return com.yandex.mapkit.map.CameraPosition(target.toNative(), zoom, azimuth, tilt)
}

fun Point.toNative(): com.yandex.mapkit.geometry.Point {
    return com.yandex.mapkit.geometry.Point(latitude, longitude)
}
