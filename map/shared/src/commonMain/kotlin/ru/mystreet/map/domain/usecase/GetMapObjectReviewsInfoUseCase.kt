package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.MapObjectReviewsInfo
import ru.mystreet.map.domain.repository.RemoteMapObjectReviewsRepository

class GetMapObjectReviewsInfoUseCase(
    private val reviewsRepository: RemoteMapObjectReviewsRepository,
) {

    suspend operator fun invoke(mapObjectId: Long): MapObjectReviewsInfo {
        return reviewsRepository.getInfo(mapObjectId)
    }

}