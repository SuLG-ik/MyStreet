package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.service.FramesService
import ru.mystreet.map.geomety.Point

class CalculateFrameForPointUseCase(
    private val framesService: FramesService,
) {

    operator fun invoke(point: Point): MapFrame {
        return framesService.calculateFrame(point)
    }

}