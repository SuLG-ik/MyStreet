package ru.mystreet.map

import android.content.Context
import android.graphics.PointF
import com.yandex.mapkit.Animation
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import dev.icerock.moko.resources.ImageResource
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.location.toImageProvider


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
        println("PISOSSSSSS ${p0.pin.geometry.latitude} ${p0.pin.geometry.longitude} ${p1}")
        println("PISOSSSSSS2 ${p0.arrow.geometry.latitude} ${p0.arrow.geometry.longitude} ${p1}")
        p0.arrow.setIcon(image.toImageProvider(context))
        p0.pin.setIcon(image.toImageProvider(context))
        p0.accuracyCircle.fillColor = RGBA(108, 176, 244, 50)
    }

}


actual class Map(
    private val context: Context,
    private val map: Map,
    private val userLocationLayer: UserLocationLayer,
) {
    private var userLocationObjectListener: UserLocationObjectListener? = null

    init {
    }

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

    actual val isFollowLocation: Boolean
        get() = userLocationLayer.isAnchorEnabled

    actual fun addPlacemark(
        point: Point,
        image: ImageResource
    ): Placemark {
        val placemark = map.mapObjects.addPlacemark()
        placemark.setIcon(image.toImageProvider(context))
        placemark.geometry = point.toNative()
        return placemark.toData()
    }

    actual fun setUserLocation(image: ImageResource) {
        userLocationObjectListener = UserLocationImage(image, context)
        userLocationLayer.setObjectListener(userLocationObjectListener)
        userLocationLayer.setDefaultSource()
        userLocationLayer.isVisible = true
    }

    actual fun followUserLocation() {
        userLocationLayer.isAutoZoomEnabled = true
        userLocationLayer.setAnchor(PointF(0.5f, 0.5f), PointF(0.5f, 0.5f))
    }

    actual fun unfollowUserLocation() {
        userLocationLayer.resetAnchor()
    }

}

private fun PlacemarkMapObject.toData(): Placemark {
    return Placemark(this)
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
