@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package ru.mystreet.map

import cocoapods.YandexMapsMobile.YMKAnimation
import cocoapods.YandexMapsMobile.YMKAnimation.Companion.animationWithType
import cocoapods.YandexMapsMobile.YMKAnimationType
import cocoapods.YandexMapsMobile.YMKCameraPosition
import cocoapods.YandexMapsMobile.YMKCameraPosition.Companion.cameraPositionWithTarget
import cocoapods.YandexMapsMobile.YMKMap
import cocoapods.YandexMapsMobile.YMKMapCameraCallback
import cocoapods.YandexMapsMobile.YMKObjectEvent
import cocoapods.YandexMapsMobile.YMKPlacemarkMapObject
import cocoapods.YandexMapsMobile.YMKPoint
import cocoapods.YandexMapsMobile.YMKPoint.Companion.pointWithLatitude
import cocoapods.YandexMapsMobile.YMKUserLocationObjectListenerProtocol
import cocoapods.YandexMapsMobile.YMKUserLocationView
import dev.icerock.moko.resources.ImageResource
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIColor
import platform.darwin.NSObject
import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude
import ru.mystreet.map.geomety.Point


fun RGBA(r: Int, g: Int, b: Int, a: Int): UIColor {
    return UIColor.colorWithRed(
        r.toDouble() / 255,
        g.toDouble() / 255,
        b.toDouble() / 255,
        a.toDouble() / 255
    )
}

@OptIn(ExperimentalForeignApi::class)
class UserLocationImage(
    private val image: ImageResource,
) : NSObject(), YMKUserLocationObjectListenerProtocol {

    override fun onObjectAddedWithView(view: YMKUserLocationView) {
        view.arrow.setIconWithImage(image.toUIImage()!!)
        view.pin.setIconWithImage(image.toUIImage()!!)
        view.accuracyCircle.fillColor = RGBA(108, 176, 244, 50)
    }

    override fun onObjectRemovedWithView(view: YMKUserLocationView) {
    }

    override fun onObjectUpdatedWithView(view: YMKUserLocationView, event: YMKObjectEvent) {

    }

}

actual class Map(
    private val map: YMKMap
) {

    actual val cameraPosition: CameraPosition
        get() = map.cameraPosition.toData()

    @OptIn(ExperimentalForeignApi::class)
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
        } else
            map.moveWithCameraPosition(
                cameraPosition = cameraPosition.toNative(),
                animation = animation.toNative(),
                cameraCallback = listener?.toNative()
            )
    }

    actual val mapObjects: MapObjects = map.mapObjects

}

private fun YMKPlacemarkMapObject.toData(): Placemark {
    return Placemark(this)
}

private fun YMKCameraPosition.toData(): CameraPosition {
    return CameraPosition(target.toData(), zoom, azimuth, tilt)
}

fun YMKPoint.toData(): Point {
    return Point(Latitude(latitude), Longitude(longitude))
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

fun Point.toNative(): YMKPoint {
    return pointWithLatitude(latitude.value, longitude.value)
}
