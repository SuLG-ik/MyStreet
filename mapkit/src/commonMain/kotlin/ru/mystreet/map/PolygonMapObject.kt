package ru.mystreet.map

import ru.mystreet.map.geomety.Point
import ru.mystreet.map.image.ImageProvider

expect class PolygonMapObject : BaseMapObject {
    override var data: Any?
}