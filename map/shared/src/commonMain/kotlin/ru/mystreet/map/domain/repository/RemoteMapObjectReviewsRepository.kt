package ru.mystreet.map.domain.repository

import ru.mystreet.map.domain.entity.MapObjectReview
import ru.mystreet.map.domain.entity.MapObjectReviewsInfo
import ru.mystreet.map.domain.entity.Pageable

interface RemoteMapObjectReviewsRepository {

    suspend fun getReviews(mapObjectId: Long, pageable: Pageable): List<MapObjectReview>

    suspend fun getInfo(mapObjectId: Long): MapObjectReviewsInfo

    suspend fun addReview(
        mapObjectId: Long,
        rating: Int,
        title: String,
        content: String,
        isAuthorHidden: Boolean,
    )

}

