package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.service.FramesService
import ru.sulgik.mapkit.map.VisibleRegion

class CalculateFramesForVisibleAreaUseCase(
    private val framesService: FramesService,
) {

    operator fun invoke(visibleArea: VisibleRegion): List<MapFrame> {
        return framesService.calculateFrames(visibleArea)
    }

}