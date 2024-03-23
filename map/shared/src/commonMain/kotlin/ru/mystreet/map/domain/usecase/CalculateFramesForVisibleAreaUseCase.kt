package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.service.FramesService
import ru.mystreet.map.geomety.VisibleArea

class CalculateFramesForVisibleAreaUseCase(
    private val framesService: FramesService,
) {

    operator fun invoke(visibleArea: VisibleArea): List<MapFrame> {
        return framesService.calculateFrames(visibleArea)
    }

}