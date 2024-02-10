package ru.mystreet.map

import com.yandex.mapkit.map.CameraListener as NativeCameraListener
import com.yandex.mapkit.map.CameraPosition as NativeCameraPosition
import com.yandex.mapkit.map.CameraUpdateReason as NativeCameraUpdateReason
import com.yandex.mapkit.map.Map as NativeMap


class MappingCameraListener(
    private val map: Map,
    private val listener: CameraListener,
) : NativeCameraListener {
    override fun onCameraPositionChanged(
        p0: NativeMap,
        p1: NativeCameraPosition,
        p2: NativeCameraUpdateReason,
        p3: Boolean
    ) {
        listener.onPositionChanged(map, p1.toData(), p2.toData(), p3)
    }
}

private fun com.yandex.mapkit.map.CameraUpdateReason.toData(): CameraUpdateReason {
    return when(this) {
        com.yandex.mapkit.map.CameraUpdateReason.GESTURES -> CameraUpdateReason.GESTURES
        com.yandex.mapkit.map.CameraUpdateReason.APPLICATION -> CameraUpdateReason.APPLICATION
    }
}
