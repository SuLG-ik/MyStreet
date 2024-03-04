package ru.mystreet.map

import com.yandex.mapkit.map.Cluster

actual class Cluster(
    private val nativeCluster: Cluster,
) {
    actual val appearance: Placemark =
        nativeCluster.appearance.toCommon()
    actual val size: Int
        get() = nativeCluster.size
}