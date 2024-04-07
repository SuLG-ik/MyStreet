package ru.mystreet.map.domain.source

import app.cash.paging.PagingSource
import ru.mystreet.map.domain.entity.MapObjectReview
import ru.mystreet.map.domain.entity.Pageable

abstract class MapObjectReviewsPagingSource : PagingSource<Pageable, MapObjectReview>() {

    data class Params(
        val mapObjectId: Long,
    )

}