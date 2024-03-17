package ru.mystreet.map.map.domain.service

import ru.mystreet.map.geomety.VisibleArea
import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.entity.MapFrame

interface FramedMapObjectsQueueService {

    suspend fun loadFramedMapObjects(
        loadedFrames: List<MapFrame>,
        visibleArea: VisibleArea,
    ): List<FramedMapObjects>

}