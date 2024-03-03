package ru.mystreet.map.map.domain.usecase

import ru.mystreet.map.geomety.VisibleArea
import ru.mystreet.map.map.domain.entity.MapFrame
import ru.mystreet.map.map.domain.service.FramesService

class CalculateFramesForVisibleAreaUseCase(
    private val framesService: FramesService,
) {

    operator fun invoke(visibleArea: VisibleArea): List<MapFrame> {
        return framesService.calculateFrames(visibleArea)
    }

}