package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.source.MapObjectReviewsPagingSource

interface GetMapObjectReviewsPagingSourceUseCase {

    operator fun invoke(mapObjectId: Long): MapObjectReviewsPagingSource

}