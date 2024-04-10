package ru.mystreet.map.map.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mystreet.core.component.onIntentWithDebounce
import ru.mystreet.errors.domain.MutableErrorDispatcher
import ru.mystreet.errors.store.onActionSafe
import ru.mystreet.errors.store.safeCoroutineExecutorFactory
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.usecase.QueueFramedMapObjectsUseCase
import ru.mystreet.map.domain.usecase.SaveMapInitialCameraPositionUseCase
import ru.mystreet.map.geomety.VisibleArea
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMviKotlinApi::class)
class FramedMapObjectsStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    errorDispatcher: MutableErrorDispatcher,
    storeFactory: StoreFactory,
    queueFramedMapObjectsUseCase: QueueFramedMapObjectsUseCase,
    saveMapInitialCameraPositionUseCase: SaveMapInitialCameraPositionUseCase,
) : FramedMapObjectsStore,
    Store<FramedMapObjectsStore.Intent, FramedMapObjectsStore.State, FramedMapObjectsStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "FramedMapObjectsStoreImpl",
        initialState = FramedMapObjectsStore.State(),
        reducer = {
            when (it) {
                is Message.AddMapObjects -> copy(
                    loadedFrames = loadedFrames + it.objects.map { it.frame },
                    loadedObjects = loadedObjects + it.objects,
                )
            }
        },
        executorFactory = safeCoroutineExecutorFactory(coroutineDispatcher, errorDispatcher) {
            onActionSafe(dispatcher = errorDispatcher) { action: Action.LoadMapObjects ->
                val loadedFrames = state().loadedFrames
                launch {
                    val objects = queueFramedMapObjectsUseCase(loadedFrames, action.visibleArea)
                    objects.map {
                        async {
                            it.collect {
                                withContext(Dispatchers.Main) {
                                    dispatch(Message.AddMapObjects(listOf(it)))
                                    publish(FramedMapObjectsStore.Label.OnLoaded(it))
                                }
                            }
                        }
                    }.awaitAll()
                }
            }
            onIntentWithDebounce<FramedMapObjectsStore.Intent.UpdateCameraPosition, _, _, _, _, _>(
                300.milliseconds
            ) {
                deferredLaunch {
                    saveMapInitialCameraPositionUseCase(it.cameraPosition)
                    withContext(Dispatchers.Main) {
                        forward(Action.LoadMapObjects(it.visibleArea))
                    }
                }
            }
        },
    ) {

    sealed interface Message {
        data class AddMapObjects(val objects: List<FramedMapObjects>) : Message
    }

    sealed interface Action {
        data class LoadMapObjects(
            val visibleArea: VisibleArea,
        ) : Action
    }
}