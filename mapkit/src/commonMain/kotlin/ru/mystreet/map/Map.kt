package ru.mystreet.map

import dev.icerock.moko.resources.ImageResource
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.location.LocationManager

expect class Map {

    val cameraPosition: CameraPosition

    fun move(cameraPosition: CameraPosition, animation: MapAnimation? = null, listener: CameraCallback? = null)

    fun addPlacemark(point: Point, image: ImageResource): Placemark

    fun setUserLocation(image: ImageResource)

    fun followUserLocation()

    fun unfollowUserLocation()

    val isFollowLocation: Boolean
}