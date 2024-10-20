package ru.mystreet.map.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.service.FramedMapObjectsQueueService
import ru.sulgik.mapkit.map.VisibleRegion

class QueueFramedMapObjectsUseCase(
    private val loadingService: FramedMapObjectsQueueService,
) {

    suspend operator fun invoke(
        loadedFrames: List<MapFrame>,
        visibleArea: VisibleRegion,
    ): List<Flow<FramedMapObjects>> {
        return loadingService.loadFramedMapObjects(loadedFrames, visibleArea)
    }

}