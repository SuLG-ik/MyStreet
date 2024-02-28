package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.repository.MapObjectsRepository

class LoadMapObjectUseCase(
    private val mapObjectsRepository: MapObjectsRepository,
) {

    suspend operator fun invoke(id: Long): MapObject {
        return mapObjectsRepository.getMapObjectById(id)
    }

}