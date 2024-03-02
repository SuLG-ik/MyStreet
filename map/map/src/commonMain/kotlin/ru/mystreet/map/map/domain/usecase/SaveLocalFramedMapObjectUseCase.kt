package ru.mystreet.map.map.domain.usecase

import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.repository.LocalFramedMapObjectsRepository

class SaveLocalFramedMapObjectUseCase(
    private val localFramedMapObjectsRepository: LocalFramedMapObjectsRepository,
) {

    suspend operator fun invoke(frame: FramedMapObjects) {
        localFramedMapObjectsRepository.saveMapObjects(frame)
    }

}