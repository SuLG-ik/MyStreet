package ru.mystreet.map.map.domain.service

import ru.mystreet.map.geomety.VisibleArea
import ru.mystreet.map.map.domain.entity.FramedMapObjects

interface FramedMapObjectsQueueService {

    suspend fun loadFramedMapObjects(visibleArea: VisibleArea): List<FramedMapObjects>

}