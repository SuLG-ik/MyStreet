package ru.mystreet.map.map.domain.usecase

import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.entity.MapFrame
import ru.mystreet.map.map.domain.repository.LocalFramedMapObjectsRepository
import ru.mystreet.map.map.domain.service.FramesService

class GetLocalFramedMapObjectUseCase(
    private val localFramedMapObjectsRepository: LocalFramedMapObjectsRepository,
) {

    suspend operator fun invoke(frame: MapFrame): FramedMapObjects? {
        return localFramedMapObjectsRepository.getMapObjects(frame)
    }

}