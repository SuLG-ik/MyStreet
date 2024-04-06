package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.AddMapObjectReviewField
import ru.mystreet.map.domain.repository.RemoteMapObjectReviewsRepository

class AddMapObjectReviewUseCase(
    private val mapObjectReviewsRepository: RemoteMapObjectReviewsRepository,
) {

    suspend operator fun invoke(mapObjectId: Long, field: AddMapObjectReviewField) {
        mapObjectReviewsRepository.addReview(
            mapObjectId = mapObjectId,
            rating = field.rating,
            title = field.title.value,
            content = field.content.value,
            isAuthorHidden = field.isAuthorHidden
        )
    }

}