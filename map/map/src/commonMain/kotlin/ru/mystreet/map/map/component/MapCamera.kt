package ru.mystreet.map.map.component

interface MapCamera {

    fun onZoomOutPress(isStart: Boolean)

    fun onZoomInPress(isStart: Boolean)

    fun onFollowLocation()

    fun onBind()

    fun onUnbind()

}