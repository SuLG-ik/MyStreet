@file:OptIn(ExperimentalForeignApi::class)

package ru.mystreet.map

import cocoapods.YandexMapsMobile.YMKAnimation
import cocoapods.YandexMapsMobile.YMKAnimation.Companion.animationWithType
import cocoapods.YandexMapsMobile.YMKAnimationType
import cocoapods.YandexMapsMobile.YMKCameraPosition
import cocoapods.YandexMapsMobile.YMKCameraPosition.Companion.cameraPositionWithTarget
import cocoapods.YandexMapsMobile.YMKMap
import cocoapods.YandexMapsMobile.YMKMapCameraCallback
import cocoapods.YandexMapsMobile.YMKPoint
import cocoapods.YandexMapsMobile.YMKPoint.Companion.pointWithLatitude
import kotlinx.cinterop.ExperimentalForeignApi
import ru.mystreet.map.geomety.Point

actual class Map(private val map: YMKMap) {

    actual val cameraPosition: CameraPosition
        get() = map.cameraPosition.toData()

    actual fun move(
        cameraPosition: CameraPosition,
        animation: MapAnimation?,
        listener: CameraCallback?
    ) {
        if (animation == null) {
            map.moveWithCameraPosition(
                cameraPosition = cameraPosition.toNative(),
                )
            listener?.onMoveFinished(true)
        }
        else
            map.moveWithCameraPosition(
                cameraPosition = cameraPosition.toNative(),
                animation = animation.toNative(),
                cameraCallback = listener?.toNative()
            )
    }


}

private fun YMKCameraPosition.toData(): CameraPosition {
    return CameraPosition(target.toData(), zoom, azimuth, tilt)
}

fun YMKPoint.toData(): Point {
    return Point(latitude, longitude)
}

class DelegatingYMKMapCameraCallback(private val callback: CameraCallback) : YMKMapCameraCallback {
    override fun invoke(p1: Boolean) {
        callback.onMoveFinished(p1)
    }
}


private fun CameraCallback.toNative(): YMKMapCameraCallback {
    return DelegatingYMKMapCameraCallback(this)
}

private fun MapAnimation.toNative(): YMKAnimation {
    return animationWithType(type.toNative(), duration)
}

private fun MapAnimation.Type.toNative(): YMKAnimationType {
    return when (this) {
        MapAnimation.Type.SMOOTH -> YMKAnimationType.YMKAnimationTypeSmooth
        MapAnimation.Type.LINEAR -> YMKAnimationType.YMKAnimationTypeLinear
    }
}

private fun CameraPosition.toNative(): YMKCameraPosition {
    return cameraPositionWithTarget(target.toNative(), zoom, azimuth, tilt)
}

private fun Point.toNative(): YMKPoint {
    return pointWithLatitude(latitude, longitude)
}
