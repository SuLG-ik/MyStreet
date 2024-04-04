package ru.mystreet.map.mapobject.component.info

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.entity.MapObjectReview

interface MapObjectReviews {

    val pagingData: Flow<PagingData<MapObjectReview>>

    fun onAddReview()

}