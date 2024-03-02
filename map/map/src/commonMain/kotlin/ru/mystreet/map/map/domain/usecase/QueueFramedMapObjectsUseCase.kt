package ru.mystreet.map.map.domain.usecase

import ru.mystreet.map.geomety.VisibleArea
import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.service.FramedMapObjectsQueueService

class QueueFramedMapObjectsUseCase(
    private val loadingService: FramedMapObjectsQueueService,
) {

    suspend operator fun invoke(
        visibleArea: VisibleArea,
    ): List<FramedMapObjects> {
        return loadingService.loadFramedMapObjects(visibleArea)
    }

}