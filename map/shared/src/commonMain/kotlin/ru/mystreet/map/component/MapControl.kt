package ru.mystreet.map.component

import ru.mystreet.app.MapController

interface MapControl {

    val controller: MapController

    val mapCamera: MapCamera

    val mapObjects: MapObjects
}