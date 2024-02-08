@file:OptIn(ExperimentalForeignApi::class)

package ru.mystreet.map

import cocoapods.YandexMapsMobile.YMKAnimation
import cocoapods.YandexMapsMobile.YMKAnimation.Companion.animationWithType
import cocoapods.YandexMapsMobile.YMKAnimationType
import cocoapods.YandexMapsMobile.YMKCameraPosition
import cocoapods.YandexMapsMobile.YMKCameraPosition.Companion.cameraPositionWithTarget
import cocoapods.YandexMapsMobile.YMKMap
import cocoapods.YandexMapsMobile.YMKMapCameraCallback
import cocoapods.YandexMapsMobile.YMKMapView
import cocoapods.YandexMapsMobile.YMKObjectEvent
import cocoapods.YandexMapsMobile.YMKPlacemarkMapObject
import cocoapods.YandexMapsMobile.YMKPoint
import cocoapods.YandexMapsMobile.YMKPoint.Companion.pointWithLatitude
import cocoapods.YandexMapsMobile.YMKUserLocationLayer
import cocoapods.YandexMapsMobile.YMKUserLocationObjectListenerProtocol
import cocoapods.YandexMapsMobile.YMKUserLocationView
import dev.icerock.moko.resources.ImageResource
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGPointMake
import platform.UIKit.UIColor
import platform.darwin.NSObject
import ru.mystreet.map.geomety.Point


fun RGBA(r: Int, g: Int, b: Int, a: Int): UIColor {
    return UIColor.colorWithRed(
        r.toDouble() / 255,
        g.toDouble() / 255,
        b.toDouble() / 255,
        a.toDouble() / 255
    )
}

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
    private val mapView: YMKMapView,
    private val map: YMKMap,
    private val userLocationLayer: YMKUserLocationLayer
) {
    private var userLocationObjectListener: UserLocationImage? = null

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

    actual fun addPlacemark(
        point: Point,
        image: ImageResource
    ): Placemark {
        val placemark = map.mapObjects.addPlacemark()
        placemark.geometry = point.toNative()
        placemark.setIconWithImage(image.toUIImage()!!)
        return placemark.toData()
    }

    actual fun setUserLocation(image: ImageResource) {
        userLocationObjectListener = UserLocationImage(image)
        userLocationLayer.setObjectListenerWithObjectListener(userLocationObjectListener)
        userLocationLayer.setDefaultSource()
        userLocationLayer.setVisibleWithOn(true)
    }

    actual fun followUserLocation() {
        userLocationLayer.setAutoZoomEnabled(true)

        userLocationLayer.setAnchorWithAnchorNormal(
            CGPointMake(mapView.mapWindow!!.width() / 2.0, mapView.mapWindow!!.height() / 2.0),
            CGPointMake(mapView.mapWindow!!.width() / 2.0, mapView.mapWindow!!.height() / 2.0),
        )
    }

    actual fun unfollowUserLocation() {
        userLocationLayer.resetAnchor()
    }

    actual val isFollowLocation: Boolean
        get() = userLocationLayer.isAnchorEnabled()


}

private fun YMKPlacemarkMapObject.toData(): Placemark {
    return Placemark(this)
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

fun Point.toNative(): YMKPoint {
    return pointWithLatitude(latitude, longitude)
}
