package ru.mystreet.map.data.service

import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.update
import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.usecase.CalculateFramesForVisibleAreaUseCase
import ru.mystreet.map.domain.usecase.GetFramedMapObjectsUseCase
import ru.mystreet.map.domain.service.FramedMapObjectsQueueService
import ru.sulgik.mapkit.map.VisibleRegion

class FramedMapObjectsQueueServiceImpl(
    private val calculateFramesForVisibleAreaUseCase: CalculateFramesForVisibleAreaUseCase,
    private val getFramedMapObjectsUseCase: GetFramedMapObjectsUseCase,
) : FramedMapObjectsQueueService {

    private val mapFramesInLoading = atomic<MutableSet<MapFrame>>(mutableSetOf())

    override suspend fun loadFramedMapObjects(
        loadedFrames: List<MapFrame>,
        visibleArea: VisibleRegion,
    ): List<Flow<FramedMapObjects>> {
        val visibleFrames =
            calculateFramesForVisibleAreaUseCase(visibleArea).filterNot { it in loadedFrames }
        if (visibleFrames.isEmpty()) {
            return emptyList()
        }
        var requestVisibleFrames: List<MapFrame>? = null
        mapFramesInLoading.update { frames ->
            requestVisibleFrames = visibleFrames.filterNot { it in frames }
            frames.addAll(visibleFrames)
            frames
        }
        val requestedFrames = requestVisibleFrames
        if (requestedFrames.isNullOrEmpty())
            return emptyList()
        val frames = requestedFrames.map { getFramedMapObjectsUseCase(it) }
        return frames
    }
}