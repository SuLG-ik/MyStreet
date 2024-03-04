package ru.mystreet.map.map.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.geomety.VisibleArea
import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.entity.MapFrame
import ru.mystreet.map.map.domain.service.FramedMapObjectsQueueService

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