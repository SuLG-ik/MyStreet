package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.MapObjectPart
import ru.mystreet.map.domain.repository.LocalFramedMapObjectsRepository

class AddLocalFramedMapObjectUseCase(
    private val localFramedMapObjectsRepository: LocalFramedMapObjectsRepository,
    private val calculateFrameForPointUseCase: CalculateFrameForPointUseCase
) {

    suspend operator fun invoke(mapObject: MapObjectPart) {
        val frame = calculateFrameForPointUseCase(mapObject.point)
        localFramedMapObjectsRepository.saveMapObject(frame, mapObject)
    }

}