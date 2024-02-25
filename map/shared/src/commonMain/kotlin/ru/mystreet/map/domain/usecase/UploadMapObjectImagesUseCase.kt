package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.repository.MapObjectsRepository

class UploadMapObjectImagesUseCase(
    private val mapObjectsRepository: MapObjectsRepository,
) {

    suspend operator fun invoke(mapObjectId: Long, images: List<ByteArray>) {
        mapObjectsRepository.uploadMapObjectImages(mapObjectId, images)
    }

}