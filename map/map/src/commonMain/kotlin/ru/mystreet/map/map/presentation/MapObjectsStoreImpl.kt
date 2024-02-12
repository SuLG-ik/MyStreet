package ru.mystreet.map.map.presentation

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
import ru.mystreet.map.map.domain.usecase.GetAllMapObjectsUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class MapObjectsStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    getAllMapObjectsUseCase: GetAllMapObjectsUseCase,
) : MapObjectsStore,
    Store<MapObjectsStore.Intent, MapObjectsStore.State, MapObjectsStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "MapObjectsStoreImpl",
        initialState = MapObjectsStore.State(),
        reducer = {
            when (it) {
                is Message.SetMapObjects -> copy(loadedMapObjects = it.objects)
            }
        },
        bootstrapper = coroutineBootstrapper(
            coroutineDispatcher
        ) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(
            coroutineDispatcher
        ) {
            onAction<Action.Setup> {
                launch {
                    val mapObjects = getAllMapObjectsUseCase()
                    withContext(Dispatchers.Main) {
                        dispatch(Message.SetMapObjects(mapObjects))
                        publish(MapObjectsStore.Label.OnMapObjectsLoaded(mapObjects))
                    }
                }
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetMapObjects(val objects: List<MapObject>) : Message
    }
}