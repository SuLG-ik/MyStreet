package ru.mystreet.map.presentation.add

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mystreet.map.domain.usecase.AddMapObjectUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class EditMapNewObjectLoadingStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    private val savedState: EditMapNewObjectLoadingStore.SavedState,
    addMapObjectUseCase: AddMapObjectUseCase,

    ) : EditMapNewObjectLoadingStore,
    Store<EditMapNewObjectLoadingStore.Intent, EditMapNewObjectLoadingStore.State, EditMapNewObjectLoadingStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "EditMapNewObjectLoadingStoreImpl",
        initialState = EditMapNewObjectLoadingStore.State(),
        reducer = {
            when (it) {
                is Message.SetLoading -> copy(isLoading = it.value)
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {
                launch {
                    addMapObjectUseCase(savedState.field)
                    withContext(Dispatchers.Main) {
                        dispatch(Message.SetLoading(false))
                        publish(EditMapNewObjectLoadingStore.Label.LoadingCompleted)
                    }
                }
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetLoading(val value: Boolean) : Message
    }

    override fun getSavedState(): EditMapNewObjectLoadingStore.SavedState {
        return savedState
    }
}