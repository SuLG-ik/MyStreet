package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.MapObjectReview
import ru.mystreet.map.domain.entity.Pageable
import ru.mystreet.map.domain.repository.RemoteMapObjectReviewsRepository

class GetRemotePagingMapObjectsUseCase(
    private val repository: RemoteMapObjectReviewsRepository,
) {

    suspend operator fun invoke(mapObjectId: Long, pageable: Pageable): List<MapObjectReview> {
        return repository.getReviews(mapObjectId, pageable)
    }

}