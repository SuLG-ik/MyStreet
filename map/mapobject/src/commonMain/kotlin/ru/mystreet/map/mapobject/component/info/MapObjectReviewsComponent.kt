package ru.mystreet.map.mapobject.component.info

import app.cash.paging.PagingData
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getStore
import ru.mystreet.map.domain.entity.MapObjectReview
import ru.mystreet.map.mapobject.presentation.info.MapObjectReviewsStore

class MapObjectReviewsComponent(
    componentContext: DIComponentContext,
    mapObjectId: Long,
   private val onAddReview: () -> Unit,
) : AppComponentContext(componentContext), MapObjectReviews {

    private val store: MapObjectReviewsStore = getStore(MapObjectReviewsStore.Params(mapObjectId))

    private val states = store.states

    override val pagingData: Flow<PagingData<MapObjectReview>> =
        states.map { it.pagingData }.filterNotNull()

    override fun onAddReview() {
        onAddReview.invoke()
    }

}