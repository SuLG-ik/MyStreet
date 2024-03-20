package ru.mystreet.map.presentation.add

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mystreet.core.component.onIntentWithDebounce
import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.domain.entity.FieldError
import ru.mystreet.map.domain.entity.FieldSuggestion
import ru.mystreet.map.domain.entity.TagsField
import ru.mystreet.map.domain.usecase.AddTagToFieldUseCase
import ru.mystreet.map.domain.usecase.FormatAndValidateDescriptionUseCase
import ru.mystreet.map.domain.usecase.FormatAndValidateTitleUseCase
import ru.mystreet.map.domain.usecase.LoadMapObjectTagsWithTitleUseCase
import ru.mystreet.map.domain.usecase.RemoveTagFromFieldUseCase
import ru.mystreet.map.geomety.Point
import ru.mystreet.map.presentation.edit.MapObjectEditStoreImpl
import ru.mystreet.uikit.ValidatedField
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMviKotlinApi::class)
class EditMapNewObjectInfoStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    savedState: EditMapNewObjectInfoStore.SavedState,
    formatAndValidateTitleUseCase: FormatAndValidateTitleUseCase,
    formatAndValidateDescriptionUseCase: FormatAndValidateDescriptionUseCase,
    loadMapObjectTagsWithTitleUseCase: LoadMapObjectTagsWithTitleUseCase,
    addTagToFieldUseCase: AddTagToFieldUseCase,
    removeTagFromFieldUseCase: RemoveTagFromFieldUseCase,
) : EditMapNewObjectInfoStore,
    Store<EditMapNewObjectInfoStore.Intent, EditMapNewObjectInfoStore.State, EditMapNewObjectInfoStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "EditMapNewObjectInfoStoreImpl",
        initialState = EditMapNewObjectInfoStore.State(
            savedState.toField(formatAndValidateTitleUseCase, formatAndValidateDescriptionUseCase)
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
                dispatch(Message.SetDescription(formatAndValidateDescriptionUseCase(it.value)))
            }
            onIntent<EditMapNewObjectInfoStore.Intent.PointInput> {
                dispatch(Message.SetPoint(it.value))
            }
            onIntentWithDebounce<EditMapNewObjectInfoStore.Intent.TagInput, _, _, _, _, _>(300.milliseconds) { intent ->
                val tags = state().field.tags
                val newTags = tags.copy(value = intent.value)
                dispatch(Message.SetTags(newTags))
                deferredLaunch {
                    val state = state()
                    val suggestions =
                        loadMapObjectTagsWithTitleUseCase(
                            state.field.category,
                            state.field.tags.value
                        )
                    val suggestedTags = state().field.tags.copy(
                        suggestion = FieldSuggestion(
                            isLoading = false,
                            suggestions = suggestions.map { it.title }.toPersistentList(),
                        )
                    )
                    withContext(Dispatchers.Main) {
                        dispatch(Message.SetTags(suggestedTags))
                    }
                }
            }
            onIntent<EditMapNewObjectInfoStore.Intent.AddTag> { intent ->
                val tags = state().field.tags
                dispatch(Message.SetTags(addTagToFieldUseCase(tags)))
            }
            onIntent<EditMapNewObjectInfoStore.Intent.RemoveTag> {
                val tags = state().field.tags
                dispatch(
                    Message.SetTags(
                        removeTagFromFieldUseCase(
                            tag = it.value,
                            field = tags
                        )
                    )
                )
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetTitle(val value: ValidatedField<FieldError>) : Message
        data class SetDescription(val value: ValidatedField<FieldError>) : Message
        data class SetPoint(val value: Point) : Message
        data class SetTags(val field: TagsField) : Message
    }

    override fun getSavedState(): EditMapNewObjectInfoStore.SavedState {
        return state.field.toSavedState()
    }
}

private fun EditMapNewObjectInfoStore.SavedState.toField(
    formatAndValidateTitleUseCase: FormatAndValidateTitleUseCase,
    formatAndValidateDescriptionUseCase: FormatAndValidateDescriptionUseCase,
): AddMapObjectField {
    return AddMapObjectField(
        title = formatAndValidateTitleUseCase(title),
        description = formatAndValidateDescriptionUseCase(description),
        point = point,
        category = category,
        tags = TagsField(
            value = tag,
            isInputAvailable = tags.size < MAX_TAGS,
            tags = TagsField.Tags(
                tags = tags,
                maxTags = MAX_TAGS,
            ),
            suggestion = FieldSuggestion(false, persistentListOf()),
        )
    )
}

private fun AddMapObjectField.toSavedState(): EditMapNewObjectInfoStore.SavedState {
    return EditMapNewObjectInfoStore.SavedState(
        title = title.value,
        description = description.value,
        point = point,
        category = category,
        tags = tags.tags.tags,
        tag = tags.value,
    )
}

private const val MAX_TAGS = 5