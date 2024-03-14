package ru.mystreet.map.presentation.info

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.usecase.LoadMapObjectUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class MapObjectInfoStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    private val savedState: MapObjectInfoStore.SavedState,
    loadMapObjectUseCase: LoadMapObjectUseCase,
) : MapObjectInfoStore,
    Store<MapObjectInfoStore.Intent, MapObjectInfoStore.State, MapObjectInfoStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "MapObjectInfoStoreImpl",
        initialState = MapObjectInfoStore.State(

        ),
        reducer = {
            when (it) {
                is Message.SetMapObject -> copy(isLoading = false, mapObject = it.mapObject)
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {
                launch {
                    val mapObject = loadMapObjectUseCase(savedState.id)
                    withContext(Dispatchers.Main) {
                        dispatch(Message.SetMapObject(mapObject))
                    }
                }
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetMapObject(val mapObject: MapObject) : Message
    }

    override fun getSavedState(): MapObjectInfoStore.SavedState {
        return savedState
    }
}