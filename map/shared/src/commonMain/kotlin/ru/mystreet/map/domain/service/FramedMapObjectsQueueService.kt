package ru.mystreet.map.domain.service

import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.geomety.VisibleArea

interface FramedMapObjectsQueueService {

    suspend fun loadFramedMapObjects(
        loadedFrames: List<MapFrame>,
        visibleArea: VisibleArea,
    ): List<Flow<FramedMapObjects>>

}