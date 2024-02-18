package ru.mystreet.map

import cocoapods.YandexMapsMobile.YMKClusterizedPlacemarkCollection
import cocoapods.YandexMapsMobile.YMKIconStyle
import dev.icerock.moko.resources.ImageResource
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGPointMake
import platform.Foundation.NSValue
import platform.UIKit.valueWithCGPoint
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.geomety.PointF

@OptIn(ExperimentalForeignApi::class)
actual class ClusterizedPlacemark(private val nativeClusterizedPlacemark: YMKClusterizedPlacemarkCollection) {
    actual fun addPlacemarks(
        points: List<Point>,
        icon: ImageResource,
        iconStyle: IconStyle
    ) {
        nativeClusterizedPlacemark.addPlacemarksWithPoints(
            points.map { it.toNative() },
            icon.toUIImage()!!,
            iconStyle.toNative(),
        )
    }

}

@OptIn(ExperimentalForeignApi::class)
private fun IconStyle.toNative(): YMKIconStyle {
    return YMKIconStyle.iconStyleWithAnchor(anchor?.toNative(), null, null, null, null, null, null)
}

@OptIn(ExperimentalForeignApi::class)
private fun PointF.toNative(): NSValue {
    return NSValue.valueWithCGPoint(CGPointMake(x.toDouble(), y.toDouble()))
}
