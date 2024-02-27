package ru.mystreet.map

import com.yandex.mapkit.map.MapObject

actual class UnknownBaseMapObject(
    private val mapObject: MapObject,
) : BaseMapObject {

    override var data: Any?
        get() = mapObject.userData
        set(value) {
            mapObject.userData = value
        }
    override var zIndex: Float
        get() = mapObject.zIndex
        set(value) {
            mapObject.zIndex = value
        }

}