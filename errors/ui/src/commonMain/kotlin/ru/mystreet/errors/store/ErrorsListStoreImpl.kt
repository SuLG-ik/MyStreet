package ru.mystreet.errors.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutorScope
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.mystreet.core.time.DateTimeService
import ru.mystreet.errors.domain.ErrorInfo
import ru.mystreet.errors.domain.MutableErrorDispatcher
import ru.mystreet.errors.domain.TimedError


@OptIn(ExperimentalMviKotlinApi::class)
class ErrorsListStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    errorsDispatcher: MutableErrorDispatcher,
    timeService: DateTimeService,
) : ErrorsListStore,
    Store<ErrorsListStore.Intent, ErrorsListStore.State, ErrorsListStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "ErrorsListStoreImpl",
        initialState = ErrorsListStore.State(
            errorsQueue = persistentListOf(),
            visibleErrors = persistentListOf(),
        ),
        reducer = {
            when (it) {
                is Message.AddToQueue -> copy(errorsQueue = errorsQueue.add(it.item))
                is Message.AddToVisible -> copy(visibleErrors = visibleErrors.add(it.item))
                is Message.RemoveFromVisible -> {
                    copy(
                        visibleErrors = visibleErrors.remove(it.item)
                    )
                }

                is Message.RemoveFromQueue -> {
                    copy(
                        errorsQueue = errorsQueue.remove(it.item)
                    )
                }
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            fun ErrorInfo.shouldBeAdded(state: ErrorsListStore.State): Boolean {
                return config.isShownMultiple ||
                        (!config.isShownMultiple && state.errorsQueue.none { it.config.key == this.config.key } && state.visibleErrors.none { it.info.config.key == this.config.key })
            }

            fun CoroutineExecutorScope<ErrorsListStore.State, Message, Action, ErrorsListStore.Label>.nextVisibleError() {
                val nextError = state().errorsQueue.getOrNull(0) ?: return
                dispatch(Message.RemoveFromQueue(nextError))
                val timedError = TimedError(nextError, timeService.currentDateTime())
                dispatch(Message.AddToVisible(timedError))
                launch(Dispatchers.Main) {
                    delay(nextError.config.duration)
                    dispatch(Message.RemoveFromVisible(timedError))
                    nextVisibleError()
                }
            }
            onAction<Action.Setup> {
                errorsDispatcher.errors.onEach {
                    val state = state()
                    if (!it.shouldBeAdded(state))
                        return@onEach
                    if (state.visibleErrors.size < 3) {
                        val timedError = TimedError(it, timeService.currentDateTime())
                        dispatch(Message.AddToVisible(timedError))
                        launch(Dispatchers.Main) {
                            delay(it.config.duration)
                            dispatch(Message.RemoveFromVisible(timedError))
                            nextVisibleError()
                        }
                    } else {
                        dispatch(Message.AddToQueue(it))
                    }
                }.flowOn(Dispatchers.Main)
                    .launchIn(this)
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class RemoveFromVisible(val item: TimedError) : Message
        data class RemoveFromQueue(val item: ErrorInfo) : Message
        data class AddToVisible(val item: TimedError) : Message
        data class AddToQueue(val item: ErrorInfo) : Message
    }

}

fun List<ErrorInfo>.getVisibleItems(timeService: DateTimeService): ImmutableList<TimedError> {
    return asSequence().take(3).map {
        TimedError(
            info = it,
            startTime = timeService.currentDateTime()
        )
    }.toImmutableList()
}