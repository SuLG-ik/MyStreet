package ru.mystreet.map.mapobject.presentation.info

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.mystreet.map.domain.entity.MapObjectReview
import ru.mystreet.map.domain.source.MapObjectReviewsPagingSource
import ru.mystreet.map.domain.usecase.GetMapObjectReviewsPagingSourceUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class MapObjectReviewsStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    getMapObjectReviewsPagingSourceUseCase: GetMapObjectReviewsPagingSourceUseCase,
    params: MapObjectReviewsPagingSource.Params,
) : MapObjectReviewsStore,
    Store<MapObjectReviewsStore.Intent, MapObjectReviewsStore.State, MapObjectReviewsStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "MapObjectReviewsStoreImpl",
        initialState = MapObjectReviewsStore.State(),
        reducer = {
            when (it) {
                is Message.UpdateData -> copy(pagingData = it.pagingData)
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {
                Pager(
                    config = PagingConfig(pageSize = 30),
                ) {
                    getMapObjectReviewsPagingSourceUseCase(params.mapObjectId)
                }.flow.cachedIn(this).onEach {
                    dispatch(Message.UpdateData(it))
                }.flowOn(Dispatchers.Main).launchIn(this)
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class UpdateData(val pagingData: PagingData<MapObjectReview>) : Message
    }
}