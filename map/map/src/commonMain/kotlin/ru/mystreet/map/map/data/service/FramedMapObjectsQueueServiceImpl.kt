package ru.mystreet.map.map.data.service

import androidx.collection.MutableScatterSet
import androidx.collection.mutableScatterSetOf
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.update
import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.geomety.VisibleArea
import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.entity.MapFrame
import ru.mystreet.map.map.domain.service.FramedMapObjectsQueueService
import ru.mystreet.map.map.domain.usecase.CalculateFramesForVisibleAreaUseCase
import ru.mystreet.map.map.domain.usecase.GetFramedMapObjectsUseCase

class FramedMapObjectsQueueServiceImpl(
    private val calculateFramesForVisibleAreaUseCase: CalculateFramesForVisibleAreaUseCase,
    private val getFramedMapObjectsUseCase: GetFramedMapObjectsUseCase,
) : FramedMapObjectsQueueService {

    private val mapFramesInLoading = atomic<MutableScatterSet<MapFrame>>(mutableScatterSetOf())

    override suspend fun loadFramedMapObjects(
        loadedFrames: List<MapFrame>,
        visibleArea: VisibleArea,
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