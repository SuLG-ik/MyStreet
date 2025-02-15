package ru.mystreet.app.feature.dialogs.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher

class DialogConfirmStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
) : DialogConfirmStore,
    Store<DialogConfirmStore.Intent, DialogConfirmStore.State, DialogConfirmStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "DialogConfirmStoreImpl",
        initialState = DialogConfirmStore.State(),
        reducer = {
            when (it) {
                is Message.SetVisible -> copy(isVisible = it.isVisible)
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<DialogConfirmStore.Intent.Confirm> {
                publish(DialogConfirmStore.Label.OnConfirm)
                dispatch(Message.SetVisible(false))
            }
            onIntent<DialogConfirmStore.Intent.Cancel> {
                publish(DialogConfirmStore.Label.OnCancel)
                dispatch(Message.SetVisible(false))
            }
            onIntent<DialogConfirmStore.Intent.Show> {
                dispatch(Message.SetVisible(true))
            }
        },
    ) {

    sealed interface Message {
        data class SetVisible(val isVisible: Boolean) : Message
    }
}
