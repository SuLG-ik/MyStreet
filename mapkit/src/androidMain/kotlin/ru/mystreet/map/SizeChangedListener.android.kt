package ru.mystreet.map

import com.yandex.mapkit.map.MapWindow as NativeMapWindow
import com.yandex.mapkit.map.SizeChangedListener as NativeSizeChangedListener


class MappingSizeChangedListener(
    private val mapWindow: MapWindow,
    private val listener: SizeChangedListener
) : NativeSizeChangedListener {

    override fun onMapWindowSizeChanged(p0: NativeMapWindow, p1: Int, p2: Int) {
        listener.onChanged(mapWindow, p1, p2)
    }

}