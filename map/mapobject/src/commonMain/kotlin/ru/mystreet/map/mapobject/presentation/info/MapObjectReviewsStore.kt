package ru.mystreet.map.mapobject.presentation.info

import app.cash.paging.PagingData
import com.arkivanov.mvikotlin.core.store.Store
import ru.mystreet.map.domain.entity.MapObjectReview
import ru.mystreet.map.domain.entity.MapObjectReviewsInfo

interface MapObjectReviewsStore :
    Store<MapObjectReviewsStore.Intent, MapObjectReviewsStore.State, MapObjectReviewsStore.Label> {

    data class Params(
        val mapObjectId: Long
    )

    sealed interface Intent {

    }

    data class State(
        val info: MapObjectReviewsInfo,
        val pagingData: PagingData<MapObjectReview>? = null,
    )

    sealed interface Label

}