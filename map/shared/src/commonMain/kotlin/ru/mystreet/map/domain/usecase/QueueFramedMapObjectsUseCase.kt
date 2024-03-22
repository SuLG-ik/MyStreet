package ru.mystreet.map.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.service.FramedMapObjectsQueueService
import ru.mystreet.map.geomety.VisibleArea

class QueueFramedMapObjectsUseCase(
    private val loadingService: FramedMapObjectsQueueService,
) {

    suspend operator fun invoke(
        loadedFrames: List<MapFrame>,
        visibleArea: VisibleArea,
    ): List<Flow<FramedMapObjects>> {
        return loadingService.loadFramedMapObjects(loadedFrames, visibleArea)
    }

}