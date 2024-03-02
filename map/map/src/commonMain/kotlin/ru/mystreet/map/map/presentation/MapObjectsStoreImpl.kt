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
import ru.mystreet.core.component.onIntentWithCoolDown
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.usecase.SaveMapInitialCameraPositionUseCase
import ru.mystreet.map.map.domain.usecase.GetAllMapObjectsUseCase
import ru.mystreet.map.map.domain.usecase.QueueFramedMapObjectsUseCase
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMviKotlinApi::class)
class MapObjectsStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    getAllMapObjectsUseCase: GetAllMapObjectsUseCase,
    queueFramedMapObjectsUseCase: QueueFramedMapObjectsUseCase,
    saveMapInitialCameraPositionUseCase: SaveMapInitialCameraPositionUseCase,
) : MapObjectsStore,
    Store<MapObjectsStore.Intent, MapObjectsStore.State, MapObjectsStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "MapObjectsStoreImpl",
        initialState = MapObjectsStore.State(),
        reducer = {
            when (it) {
                is Message.SetMapObjects -> copy(loadedMapObjects = it.objects)
                is Message.SetMapCategories -> copy(categories = it.categories)
            }
        },
        bootstrapper = coroutineBootstrapper(
            coroutineDispatcher
        ) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(
            coroutineDispatcher
        ) {
            onAction<Action.Setup> {
                val categories = state().categories.orEmpty()
                launch {
                    val mapObjects = getAllMapObjectsUseCase(categories)
                    withContext(Dispatchers.Main) {
                        dispatch(Message.SetMapObjects(mapObjects))
                        publish(MapObjectsStore.Label.OnMapObjectsLoaded(mapObjects))
                    }
                }
            }
//            onIntent<MapObjectsStore.Intent.SetMapObjectCategories> { intent ->
//                val (newCategories, categoriesToRemove) = getCategoriesDifferUseCase(
//                    state().categories,
//                    intent.categories
//                )
//                dispatch(Message.SetMapCategories(newCategories))
//                if (categoriesToRemove.isNotEmpty())
//                    publish(MapObjectsStore.Label.OnRemoveMapObjectsCategories(categoriesToRemove))
//                launch {
//                    val mapObjects = getAllMapObjectsUseCase(newCategories)
//                    withContext(Dispatchers.Main) {
//                        dispatch(Message.SetMapObjects(mapObjects))
//                        publish(MapObjectsStore.Label.OnMapObjectsLoaded(mapObjects))
//                    }
//                }
//            }
            onIntentWithCoolDown<MapObjectsStore.Intent.UpdateCameraPosition, _, _, _, _, _>(1.seconds) {
                launch {
                    saveMapInitialCameraPositionUseCase(it.cameraPosition)
                }
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetMapObjects(val objects: List<MapObject>) : Message
        data class SetMapCategories(val categories: List<MapObjectCategory>) : Message
    }
}