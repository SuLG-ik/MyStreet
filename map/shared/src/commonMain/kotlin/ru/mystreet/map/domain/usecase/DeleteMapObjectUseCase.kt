package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.repository.MapObjectsRepository

class DeleteMapObjectUseCase(
    private val mapObjectsRepository: MapObjectsRepository,
) {


    suspend operator fun invoke(id: Long) {
        mapObjectsRepository.deleteMapObject(id)
    }

}