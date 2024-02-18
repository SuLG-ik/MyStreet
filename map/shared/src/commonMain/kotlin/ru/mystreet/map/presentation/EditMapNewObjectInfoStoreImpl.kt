package ru.mystreet.map.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.geomety.Point

@OptIn(ExperimentalMviKotlinApi::class)
class EditMapNewObjectInfoStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    savedState: EditMapNewObjectInfoStore.SavedState,
) : EditMapNewObjectInfoStore,
    Store<EditMapNewObjectInfoStore.Intent, EditMapNewObjectInfoStore.State, EditMapNewObjectInfoStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "EditMapNewObjectInfoStoreImpl",
        initialState = EditMapNewObjectInfoStore.State(
            savedState.toField()
        ),
        reducer = {
            when (it) {
                is Message.SetDescription -> copy(field = field.copy(description = it.value))
                is Message.SetTitle -> copy(field = field.copy(title = it.value))
                is Message.SetPoint -> copy(field = field.copy(point = it.value))
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {

            }
            onIntent<EditMapNewObjectInfoStore.Intent.TitleInput> {
                dispatch(Message.SetTitle(it.value))
            }
            onIntent<EditMapNewObjectInfoStore.Intent.DescriptionInput> {
                dispatch(Message.SetDescription(it.value))
            }
            onIntent<EditMapNewObjectInfoStore.Intent.PointInput> {
                dispatch(Message.SetPoint(it.value))
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetTitle(val value: String) : Message
        data class SetDescription(val value: String) : Message
        data class SetPoint(val value: Point) : Message
    }

    override fun getSavedState(): EditMapNewObjectInfoStore.SavedState {
        return state.field.toSavedState()
    }
}

private fun EditMapNewObjectInfoStore.SavedState.toField(): AddMapObjectField {
    return AddMapObjectField(
        title = title, description = description, point = point, category = category
    )
}

private fun AddMapObjectField.toSavedState(): EditMapNewObjectInfoStore.SavedState {
    return EditMapNewObjectInfoStore.SavedState(
        title = title, description = description, point = point, category = category
    )
}
