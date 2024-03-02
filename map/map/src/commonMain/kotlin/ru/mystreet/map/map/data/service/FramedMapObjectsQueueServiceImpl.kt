package ru.mystreet.map.map.data.service

import androidx.collection.MutableScatterSet
import androidx.collection.mutableScatterSetOf
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.update
import kotlinx.atomicfu.updateAndGet
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

    override suspend fun loadFramedMapObjects(visibleArea: VisibleArea): List<FramedMapObjects> {
        val visibleFrames = calculateFramesForVisibleAreaUseCase(visibleArea)
        if (visibleFrames.isEmpty()) {
            return emptyList()
        }
        val newMapFrames = mapFramesInLoading.updateAndGet {
            it.addAll(visibleFrames)
            it
        }
        val requestVisibleFrames = visibleFrames.filterNot { it in newMapFrames }
        val frames = getFramedMapObjectsUseCase(visibleFrames)
        mapFramesInLoading.update {
            it.removeAll(requestVisibleFrames)
            it
        }
        return frames
    }
}