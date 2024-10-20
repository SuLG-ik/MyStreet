package ru.mystreet.map.domain.service

import ru.mystreet.map.domain.entity.MapFrame
import ru.sulgik.mapkit.geometry.Point
import ru.sulgik.mapkit.map.VisibleRegion

interface FramesService {

    fun calculateFrames(visibleArea: VisibleRegion): List<MapFrame>

    fun calculateFrame(point: Point): MapFrame

}