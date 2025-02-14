package ru.mystreet.app.feature.dialogs.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher

class DialogCustomStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
) : DialogCustomStore,
    Store<DialogCustomStore.Intent, DialogCustomStore.State, DialogCustomStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "DialogCustomStoreImpl",
        initialState = DialogCustomStore.State(),
        reducer = {
            when (it) {
                is Message.SetVisible -> copy(isVisible = it.isVisible)
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<DialogCustomStore.Intent.Show> {
                publish(DialogCustomStore.Label.OnShow)
                dispatch(Message.SetVisible(true))
            }
            onIntent<DialogCustomStore.Intent.Hide> {
                publish(DialogCustomStore.Label.OnHide)
                dispatch(Message.SetVisible(false))
            }
        },
    ) {

    sealed interface Message {
        data class SetVisible(val isVisible: Boolean) : Message
    }
}
