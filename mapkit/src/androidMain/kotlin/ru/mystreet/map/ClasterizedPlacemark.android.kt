package ru.mystreet.map

import com.yandex.mapkit.map.ClusterizedPlacemarkCollection
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.geomety.PointF
import ru.mystreet.map.image.ImageProvider

actual class ClusterizedPlacemark(
    val nativeClusterizedPlacemark: ClusterizedPlacemarkCollection,
) {
    actual fun addPlacemarks(
        points: List<Point>,
        icon: ImageProvider,
        iconStyle: IconStyle,
    ): List<Placemark> {
        return nativeClusterizedPlacemark.addPlacemarks(
            points.map { it.toNative() },
            icon.toNative(),
            iconStyle.toNative(),
        ).map {
            it.toCommon()
        }
    }

    actual fun visit(visitor: MapObjectVisitor) {
        nativeClusterizedPlacemark.traverse(visitor)
    }

    actual fun clusterPlacemarks(clusterRadius: Double, minZoom: Int) {
        nativeClusterizedPlacemark.clusterPlacemarks(clusterRadius, minZoom)
    }

    actual fun remove(placemark: Placemark) {
        nativeClusterizedPlacemark.remove(placemark.nativePlacemark)
    }

}

internal fun IconStyle.toNative(): com.yandex.mapkit.map.IconStyle {
    return com.yandex.mapkit.map.IconStyle(
        anchor?.toNative(),
        null,
        null,
        null,
        isVisible,
        null,
        null
    )
}

private fun PointF.toNative(): android.graphics.PointF {
    return android.graphics.PointF(x, y)
}
