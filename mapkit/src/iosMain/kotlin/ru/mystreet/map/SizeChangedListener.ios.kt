package ru.mystreet.map

import cocoapods.YandexMapsMobile.YMKMapSizeChangedListenerProtocol
import cocoapods.YandexMapsMobile.YMKMapWindow
import kotlinx.cinterop.ExperimentalForeignApi
import platform.darwin.NSInteger
import platform.darwin.NSObject


@OptIn(ExperimentalForeignApi::class)
class MappingSizeChangedListener(
    private val mapWindow: MapWindow,
    private val listener: SizeChangedListener
) : NSObject(), YMKMapSizeChangedListenerProtocol {

    override fun onMapWindowSizeChangedWithMapWindow(
        mapWindow: YMKMapWindow,
        newWidth: NSInteger,
        newHeight: NSInteger
    ) {
        listener.onChanged(this.mapWindow, newWidth.toInt(), newWidth.toInt())
    }

}