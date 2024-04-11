package ru.mystreet.map.mapobject.presentation.info

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mystreet.errors.domain.ErrorDispatcher
import ru.mystreet.errors.store.onActionSafe
import ru.mystreet.errors.store.onIntentSafeDebounce
import ru.mystreet.errors.store.safeCoroutineExecutorFactory
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.usecase.LoadMapObjectUseCase
import ru.mystreet.map.domain.usecase.SetMapObjectFavouriteUseCase
import ru.mystreet.map.presentation.info.MapObjectInfoStore
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMviKotlinApi::class)
class MapObjectInfoStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    errorDispatcher: ErrorDispatcher,
    private val savedState: MapObjectInfoStore.SavedState,
    loadMapObjectUseCase: LoadMapObjectUseCase,
    setMapObjectFavouriteUseCase: SetMapObjectFavouriteUseCase,
) : MapObjectInfoStore,
    Store<MapObjectInfoStore.Intent, MapObjectInfoStore.State, MapObjectInfoStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "MapObjectInfoStoreImpl",
        initialState = MapObjectInfoStore.State(),
        reducer = {
            when (it) {
                is Message.SetMapObject -> copy(isLoading = false, mapObject = it.mapObject)
                is Message.SetFavourite -> copy(
                    mapObject = mapObject?.copy(
                        forUser = mapObject.forUser?.copy(
                            isFavourite = mapObject.forUser?.isFavourite ?: false,
                        )
                    )
                )
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = safeCoroutineExecutorFactory(coroutineDispatcher, errorDispatcher) {
            onActionSafe(errorDispatcher) { action: Action.Setup ->
                launch {
                    val mapObject = loadMapObjectUseCase(savedState.id)
                    withContext(Dispatchers.Main) {
                        dispatch(Message.SetMapObject(mapObject))
                    }
                }
            }
            onIntentSafeDebounce(
                duration = 300.milliseconds,
                dispatcher = errorDispatcher
            ) { intent: MapObjectInfoStore.Intent.SetFavourite ->
                deferredLaunch {
                    setMapObjectFavouriteUseCase(savedState.id, intent.value)
                    withContext(Dispatchers.Main) {
                        dispatch(Message.SetFavourite(intent.value))
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
        data class SetFavourite(val value: Boolean) : Message
    }

    override fun getSavedState(): MapObjectInfoStore.SavedState {
        return savedState
    }
}