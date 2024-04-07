package ru.mystreet.map.data.source

import app.cash.paging.PagingState
import ru.mystreet.map.domain.entity.MapObjectReview
import ru.mystreet.map.domain.entity.Pageable
import ru.mystreet.map.domain.source.MapObjectReviewsPagingSource
import ru.mystreet.map.domain.usecase.CalculateNextPageUseCase
import ru.mystreet.map.domain.usecase.GetRemotePagingMapObjectsUseCase

class RemoteMapObjectReviewsPagingSource(
    private val calculateNextPageUseCase: CalculateNextPageUseCase,
    private val getRemotePagingMapObjectsUseCase: GetRemotePagingMapObjectsUseCase,
    private val sourceParams: Params,
) : MapObjectReviewsPagingSource() {
    override fun getRefreshKey(state: PagingState<Pageable, MapObjectReview>): Pageable? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
            Pageable(
                anchorPage.prevKey?.page?.plus(1) ?: anchorPage.nextKey?.page?.minus(1)
                ?: return null,
                state.config.pageSize,
            )
        }
    }

    override suspend fun load(params: LoadParams<Pageable>): LoadResult<Pageable, MapObjectReview> {
        try {
            val nextPageNumber = params.key ?: Pageable(0, 30)
            val response =
                getRemotePagingMapObjectsUseCase(sourceParams.mapObjectId, nextPageNumber)
            return LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = calculateNextPageUseCase(nextPageNumber, response)
            )
        } catch (e: Exception) {
            throw e
        }
    }
}