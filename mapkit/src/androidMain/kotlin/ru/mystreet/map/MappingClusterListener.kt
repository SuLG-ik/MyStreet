package ru.mystreet.map

import android.content.Context
import com.yandex.mapkit.map.Cluster
import com.yandex.mapkit.map.ClusterListener

class MappingClusterListener(
    private val clusterListener: ru.mystreet.map.ClusterListener,
) : ClusterListener {
    override fun onClusterAdded(p0: Cluster) {
        clusterListener.onUpdate(Cluster(p0))
    }

}
