package ru.mystreet.map

fun interface CameraListener {

    fun onPositionChanged(
        map: Map,
        cameraPosition: CameraPosition,
        reason: CameraUpdateReason,
        isFinished: Boolean,
    )

}