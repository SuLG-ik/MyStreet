package ru.mystreet.map.map.domain.service

import ru.mystreet.map.geomety.VisibleArea
import ru.mystreet.map.map.domain.entity.MapFrame

interface FramesService {

    fun calculateFrames(visibleArea: VisibleArea): List<MapFrame>

}