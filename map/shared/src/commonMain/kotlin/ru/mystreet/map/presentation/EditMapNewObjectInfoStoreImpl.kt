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
                is Message.SetTag -> copy(field = field.copy(tags = field.tags.copy(tag = it.value)))
                is Message.SetTags -> copy(
                    field = field.copy(
                        tags = field.tags.copy(
                            tag = it.tag,
                            tags = it.tags,
                            isInputAvailable = it.tags.size < field.tags.maxTags
                        )
                    )
                )
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
            onIntent<EditMapNewObjectInfoStore.Intent.TagInput> {
                dispatch(Message.SetTag(it.value))
            }
            onIntent<EditMapNewObjectInfoStore.Intent.AddTag> {
                dispatch(Message.SetTags(state().field.tags.tags + state().field.tags.tag, ""))
            }
            onIntent<EditMapNewObjectInfoStore.Intent.RemoveTag> { intent ->
                dispatch(
                    Message.SetTags(
                        state().field.tags.tags.filterNot { it == intent.value },
                        state().field.tags.tag
                    )
                )
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
        data class SetTag(val value: String) : Message
        data class SetTags(val tags: List<String>, val tag: String) : Message
    }

    override fun getSavedState(): EditMapNewObjectInfoStore.SavedState {
        return state.field.toSavedState()
    }
}

private fun EditMapNewObjectInfoStore.SavedState.toField(): AddMapObjectField {
    return AddMapObjectField(
        title = title,
        description = description,
        point = point,
        category = category,
        tags = AddMapObjectField.Tags(
            tag = tag,
            tags = tags,
            maxTags = MAX_TAGS,
            isInputAvailable = tags.size < MAX_TAGS,
        )
    )
}

private fun AddMapObjectField.toSavedState(): EditMapNewObjectInfoStore.SavedState {
    return EditMapNewObjectInfoStore.SavedState(
        title = title,
        description = description,
        point = point,
        category = category,
        tags = tags.tags,
        tag = tags.tag
    )
}

private const val MAX_TAGS = 5