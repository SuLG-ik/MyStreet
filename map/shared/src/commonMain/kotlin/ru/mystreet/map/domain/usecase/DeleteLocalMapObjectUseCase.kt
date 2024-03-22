package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.repository.LocalFramedMapObjectsRepository

class DeleteLocalMapObjectUseCase(
    private val mapObjectsRepository: LocalFramedMapObjectsRepository,
) {

    suspend operator fun invoke(id: Long) {
        mapObjectsRepository.deleteMapObject(id)
    }

}