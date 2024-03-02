package ru.mystreet.map.map.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mystreet.core.component.onIntentWithDebounce
import ru.mystreet.map.domain.usecase.SaveMapInitialCameraPositionUseCase
import ru.mystreet.map.geomety.VisibleArea
import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.usecase.QueueFramedMapObjectsUseCase
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
        initialState = FramedMapObjectsStore.State(
            loadedObjects = emptyList(),
        ),
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
                launch {
                    val objects = queueFramedMapObjectsUseCase(it.visibleArea)
                    withContext(Dispatchers.Main) {
                        dispatch(Message.AddMapObjects(objects))
                        publish(FramedMapObjectsStore.Label.OnLoaded(objects.flatMap { it.objects }))
                    }
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