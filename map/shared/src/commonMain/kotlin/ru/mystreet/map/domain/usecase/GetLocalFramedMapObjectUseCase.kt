package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.repository.LocalFramedMapObjectsRepository

class GetLocalFramedMapObjectUseCase(
    private val localFramedMapObjectsRepository: LocalFramedMapObjectsRepository,
) {

    suspend operator fun invoke(frame: MapFrame): FramedMapObjects? {
        return localFramedMapObjectsRepository.getMapObjects(frame)
    }

}