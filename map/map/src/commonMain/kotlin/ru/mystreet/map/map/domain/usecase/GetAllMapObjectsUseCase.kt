package ru.mystreet.map.map.domain.usecase

import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.repository.MapObjectsRepository

class GetAllMapObjectsUseCase(
    private val mapObjectsRepository: MapObjectsRepository
) {

    suspend operator fun invoke(): List<MapObject> {
        return mapObjectsRepository.getAllMapObjects()
    }

}