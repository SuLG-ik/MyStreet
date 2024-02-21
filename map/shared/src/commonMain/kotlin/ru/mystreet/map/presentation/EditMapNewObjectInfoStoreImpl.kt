package ru.mystreet.map.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mystreet.core.component.onIntentWithDebounce
import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.domain.entity.FieldSuggestion
import ru.mystreet.map.domain.entity.TagsField
import ru.mystreet.map.domain.entity.TitleField
import ru.mystreet.map.domain.usecase.FormatAndValidateTitleUseCase
import ru.mystreet.map.domain.usecase.LoadMapObjectTagsWithTitleUseCase
import ru.mystreet.map.geomety.Point
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMviKotlinApi::class)
class EditMapNewObjectInfoStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    savedState: EditMapNewObjectInfoStore.SavedState,
    formatAndValidateTitleUseCase: FormatAndValidateTitleUseCase,
    loadMapObjectTagsWithTitleUseCase: LoadMapObjectTagsWithTitleUseCase,
) : EditMapNewObjectInfoStore,
    Store<EditMapNewObjectInfoStore.Intent, EditMapNewObjectInfoStore.State, EditMapNewObjectInfoStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "EditMapNewObjectInfoStoreImpl",
        initialState = EditMapNewObjectInfoStore.State(
            savedState.toField(formatAndValidateTitleUseCase)
        ),
        reducer = {
            when (it) {
                is Message.SetDescription -> copy(field = field.copy(description = it.value))
                is Message.SetTitle -> copy(field = field.copy(title = it.value))
                is Message.SetPoint -> copy(field = field.copy(point = it.value))
                is Message.SetTags -> copy(
                    field = field.copy(tags = it.field),
                )
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {

            }
            onIntent<EditMapNewObjectInfoStore.Intent.TitleInput> {
                dispatch(Message.SetTitle(formatAndValidateTitleUseCase(it.value)))
            }
            onIntent<EditMapNewObjectInfoStore.Intent.DescriptionInput> {
                dispatch(Message.SetDescription(it.value))
            }
            onIntent<EditMapNewObjectInfoStore.Intent.PointInput> {
                dispatch(Message.SetPoint(it.value))
            }
            onIntentWithDebounce<EditMapNewObjectInfoStore.Intent.TagInput, _, _, _, _, _>(300.milliseconds) { intent ->
                val tags = state().field.tags
                val newTags = tags.copy(value = intent.value)
                dispatch(Message.SetTags(newTags))
                debouncedLaunch {
                    val state = state()
                    val suggestions =
                        loadMapObjectTagsWithTitleUseCase(
                            state.field.category,
                            state.field.tags.value
                        )
                    val suggestedTags = state().field.tags.copy(
                        suggestion = FieldSuggestion(
                            isLoading = false,
                            suggestions = suggestions.map { it.title },
                        )
                    )
                    withContext(Dispatchers.Main) {
                        dispatch(Message.SetTags(suggestedTags))
                    }
                }
            }
            onIntent<EditMapNewObjectInfoStore.Intent.AddTag> { intent ->
                val tags = state().field.tags
                val newCurrentTags = tags.tags.tags + tags.value
                val newTags = tags.copy(
                    tags = tags.tags.copy(tags = newCurrentTags),
                    isInputAvailable = newCurrentTags.size < MAX_TAGS, value = ""
                )
                dispatch(Message.SetTags(newTags))
            }
            onIntent<EditMapNewObjectInfoStore.Intent.RemoveTag> { intent ->
                val tags = state().field.tags
                val newCurrentTags = tags.tags.tags.filter { it != intent.value }
                val newTags = tags.copy(
                    tags = tags.tags.copy(tags = newCurrentTags),
                    isInputAvailable = newCurrentTags.size < MAX_TAGS,
                )
                dispatch(Message.SetTags(newTags))
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetTitle(val value: TitleField) : Message
        data class SetDescription(val value: String) : Message
        data class SetPoint(val value: Point) : Message
        data class SetTags(val field: TagsField) : Message
    }

    override fun getSavedState(): EditMapNewObjectInfoStore.SavedState {
        return state.field.toSavedState()
    }
}

private fun EditMapNewObjectInfoStore.SavedState.toField(formatAndValidateTitleUseCase: FormatAndValidateTitleUseCase): AddMapObjectField {
    return AddMapObjectField(
        title = formatAndValidateTitleUseCase(title),
        description = description,
        point = point,
        category = category,
        tags = TagsField(
            value = tag,
            isInputAvailable = tags.size < MAX_TAGS,
            tags = TagsField.Tags(
                tags = tags,
                maxTags = MAX_TAGS,
            ),
            suggestion = FieldSuggestion(false, emptyList()),
        )
    )
}

private fun AddMapObjectField.toSavedState(): EditMapNewObjectInfoStore.SavedState {
    return EditMapNewObjectInfoStore.SavedState(
        title = title.value,
        description = description,
        point = point,
        category = category,
        tags = tags.tags.tags,
        tag = tags.value,
    )
}

private const val MAX_TAGS = 5