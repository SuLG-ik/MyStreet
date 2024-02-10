package ru.mystreet.map.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory

@OptIn(ExperimentalMviKotlinApi::class)
class EditMapStoreImpl(
    coroutineDispatcher: kotlinx.coroutines.CoroutineDispatcher,
    storeFactory: StoreFactory,
    savedState: EditMapStore.SavedState,
) : EditMapStore,
    Store<EditMapStore.Intent, EditMapStore.State, EditMapStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "EditMapStoreImpl",
        initialState = EditMapStore.State(
            isEnabled = savedState.isEnabled,
        ),
        reducer = {
            when (it) {
                is Message.SetEnabled -> copy(
                    isEnabled = it.value,
                )
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {

            }
            onIntent<EditMapStore.Intent.ToggleEnabled> {
                dispatch(Message.SetEnabled(!state().isEnabled))
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetEnabled(val value: Boolean) : Message
    }

    override fun getSavedState(): EditMapStore.SavedState {
        return EditMapStore.SavedState(state.isEnabled)
    }

}