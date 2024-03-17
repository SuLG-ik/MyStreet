package ru.mystreet.map

import android.content.Context
import com.yandex.mapkit.map.Cluster

actual class Cluster(
    private val nativeCluster: Cluster,
    context: Context,
) {
    actual val appearance: Placemark =
        nativeCluster.appearance.toCommon(context)
    actual val size: Int
        get() = nativeCluster.size
}