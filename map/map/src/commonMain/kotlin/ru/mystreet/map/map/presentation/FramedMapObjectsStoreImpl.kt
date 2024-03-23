package ru.mystreet.map.map.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mystreet.core.component.onIntentWithDebounce
import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.usecase.SaveMapInitialCameraPositionUseCase
import ru.mystreet.map.geomety.VisibleArea
import ru.mystreet.map.domain.usecase.QueueFramedMapObjectsUseCase
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMviKotlinApi::class)
class FramedMapObjectsStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
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
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.LoadMapObjects> {
                val loadedFrames = state().loadedFrames
                launch {
                    val objects = queueFramedMapObjectsUseCase(loadedFrames, it.visibleArea)
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