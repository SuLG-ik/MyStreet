package ru.mystreet.map

fun interface SizeChangedListener {

    fun onChanged(window: MapWindow, newWidth: Int, newHeight: Int)

}