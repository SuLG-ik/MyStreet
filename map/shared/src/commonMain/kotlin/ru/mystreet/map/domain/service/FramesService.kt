package ru.mystreet.map.domain.service

import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.geomety.VisibleArea

interface FramesService {

    fun calculateFrames(visibleArea: VisibleArea): List<MapFrame>

    fun calculateFrame(point: Point): MapFrame

}