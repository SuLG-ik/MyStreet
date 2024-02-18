package ru.mystreet.map

import android.content.Context
import com.yandex.mapkit.map.ClusterizedPlacemarkCollection
import dev.icerock.moko.resources.ImageResource
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.geomety.PointF
import ru.mystreet.map.location.toImageProvider

actual class ClusterizedPlacemark(
    private val nativeClusterizedPlacemark: ClusterizedPlacemarkCollection,
    private val context: Context,
) {
    actual fun addPlacemarks(
        points: List<Point>,
        icon: ImageResource,
        iconStyle: IconStyle
    ) {
        nativeClusterizedPlacemark.addPlacemarks(
            points.map { it.toNative() },
            icon.toImageProvider(context),
            iconStyle.toNative(),
        )
    }

}

private fun IconStyle.toNative(): com.yandex.mapkit.map.IconStyle {
    return com.yandex.mapkit.map.IconStyle(anchor?.toNative(), null, null, null, null, null, null)
}

private fun PointF.toNative(): android.graphics.PointF {
    return android.graphics.PointF(x, y)
}
