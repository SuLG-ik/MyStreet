package ru.mystreet.map.data.usecase

import ru.mystreet.map.data.source.RemoteMapObjectReviewsPagingSource
import ru.mystreet.map.domain.source.MapObjectReviewsPagingSource
import ru.mystreet.map.domain.usecase.CalculateNextPageUseCase
import ru.mystreet.map.domain.usecase.GetMapObjectReviewsPagingSourceUseCase
import ru.mystreet.map.domain.usecase.GetRemotePagingMapObjectsUseCase

class GetMapObjectReviewsPagingSourceUseCaseImpl(
    private val calculateNextPageUseCase: CalculateNextPageUseCase,
    private val getRemotePagingMapObjectsUseCase: GetRemotePagingMapObjectsUseCase,
) : GetMapObjectReviewsPagingSourceUseCase {
    override fun invoke(mapObjectId: Long): MapObjectReviewsPagingSource {
        return RemoteMapObjectReviewsPagingSource(
            calculateNextPageUseCase = calculateNextPageUseCase,
            getRemotePagingMapObjectsUseCase = getRemotePagingMapObjectsUseCase,
            sourceParams = MapObjectReviewsPagingSource.Params(mapObjectId)
        )
    }
}