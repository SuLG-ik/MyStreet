package ru.mystreet.map.mapobject.component.info

import app.cash.paging.PagingData
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.domain.entity.MapObjectReview
import ru.mystreet.map.domain.entity.MapObjectReviewsInfo

interface MapObjectReviews {

    val pagingData: Flow<PagingData<MapObjectReview>>

    val info: Value<MapObjectReviewsInfo>

    fun onAddReview()

}