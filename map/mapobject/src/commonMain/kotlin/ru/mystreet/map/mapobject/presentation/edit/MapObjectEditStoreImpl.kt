package ru.mystreet.map.presentation.edit

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mystreet.core.component.onIntentWithDebounce
import ru.mystreet.core.component.onIntentWithSkipping
import ru.mystreet.map.domain.entity.EditMapObjectField
import ru.mystreet.map.domain.entity.FieldError
import ru.mystreet.map.domain.entity.FieldSuggestion
import ru.mystreet.map.domain.entity.TagsField
import ru.mystreet.map.domain.usecase.AddTagToFieldUseCase
import ru.mystreet.map.domain.usecase.EditMapObjectUseCase
import ru.mystreet.map.domain.usecase.FormatAndValidateDescriptionUseCase
import ru.mystreet.map.domain.usecase.FormatAndValidateTitleUseCase
import ru.mystreet.map.domain.usecase.LoadMapObjectTagsWithTitleUseCase
import ru.mystreet.map.domain.usecase.LoadMapObjectUseCase
import ru.mystreet.map.domain.usecase.DeleteMapObjectUseCase
import ru.mystreet.map.domain.usecase.RemoveTagFromFieldUseCase
import ru.mystreet.uikit.ValidatedField
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMviKotlinApi::class)
class MapObjectEditStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    savedState: MapObjectEditStore.SavedState,
    formatAndValidateTitleUseCase: FormatAndValidateTitleUseCase,
    formatAndValidateDescriptionUseCase: FormatAndValidateDescriptionUseCase,
    loadMapObjectTagsWithTitleUseCase: LoadMapObjectTagsWithTitleUseCase,
    loadMapObjectUseCase: LoadMapObjectUseCase,
    removeTagFromFieldUseCase: RemoveTagFromFieldUseCase,
    addTagToFieldUseCase: AddTagToFieldUseCase,
    editMapObjectUseCase: EditMapObjectUseCase,
    deleteMapObjectUseCase: DeleteMapObjectUseCase,
) : MapObjectEditStore,
    Store<MapObjectEditStore.Intent, MapObjectEditStore.State, MapObjectEditStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "MapObjectEditStoreImpl",
        initialState = savedState.toState(
            formatAndValidateTitleUseCase,
            formatAndValidateDescriptionUseCase
        ),
        reducer = {
            when (it) {
                is Message.SetDescription -> copy(field = field?.copy(description = it.value))
                is Message.SetTitle -> copy(field = field?.copy(title = it.value))
                is Message.SetTags -> copy(
                    field = field?.copy(tags = it.field),
                )

                is Message.SetField -> copy(isLoading = false, field = it.field)
                Message.Loading -> copy(isLoading = true)
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {
                launch {
                    val mapObject = loadMapObjectUseCase(savedState.id)
                    withContext(Dispatchers.Main) {
                        dispatch(
                            Message.SetField(
                                EditMapObjectField(
                                    id = mapObject.id,
                                    category = mapObject.category,
                                    title = ValidatedField(mapObject.title, null),
                                    description = ValidatedField(mapObject.description, null),
                                    tags = TagsField(
                                        isInputAvailable = mapObject.tags.size < MAX_TAGS,
                                        value = "",
                                        tags = TagsField.Tags(
                                            mapObject.tags.map { it.title }.toPersistentList(),
                                            maxTags = MAX_TAGS,
                                        ),
                                        suggestion = FieldSuggestion(false, persistentListOf()),
                                    )
                                )
                            )
                        )
                    }
                }
            }
            onIntent<MapObjectEditStore.Intent.TitleInput> {
                dispatch(Message.SetTitle(formatAndValidateTitleUseCase(it.value)))
            }
            onIntent<MapObjectEditStore.Intent.DescriptionInput> {
                dispatch(Message.SetDescription(formatAndValidateDescriptionUseCase(it.value)))
            }
            onIntentWithDebounce<MapObjectEditStore.Intent.TagInput, _, _, _, _, _>(300.milliseconds) { intent ->
                val tags = state().field?.tags ?: return@onIntentWithDebounce
                val newTags = tags.copy(value = intent.value)
                dispatch(Message.SetTags(newTags))
                deferredLaunch {
                    val field = state().field ?: return@deferredLaunch
                    val suggestions =
                        loadMapObjectTagsWithTitleUseCase(
                            field.category,
                            field.tags.value
                        )
                    val suggestedTags = state().field?.tags?.copy(
                        suggestion = FieldSuggestion(
                            isLoading = false,
                            suggestions = suggestions.map { it.title }.toPersistentList(),
                        )
                    ) ?: return@deferredLaunch
                    withContext(Dispatchers.Main) {
                        dispatch(Message.SetTags(suggestedTags))
                    }
                }
            }
            onIntent<MapObjectEditStore.Intent.AddTag> { intent ->
                val tags = state().field?.tags ?: return@onIntent
                dispatch(Message.SetTags(addTagToFieldUseCase(tags)))
            }
            onIntent<MapObjectEditStore.Intent.RemoveTag> {
                val tags = state().field?.tags ?: return@onIntent
                dispatch(Message.SetTags(removeTagFromFieldUseCase(tag = it.value, field = tags)))
            }
            onIntentWithSkipping { intent: MapObjectEditStore.Intent.Continue ->
                val field = state().field ?: return@onIntentWithSkipping
                dispatch(Message.Loading)
                deferredLaunch {
                    editMapObjectUseCase(field)
                    withContext(Dispatchers.Main) {
                        publish(MapObjectEditStore.Label.OnSaved)
                    }
                }
            }
            onIntentWithSkipping { intent: MapObjectEditStore.Intent.Delete ->
                val id = state().id
                dispatch(Message.Loading)
                deferredLaunch {
                    deleteMapObjectUseCase(id)
                    withContext(Dispatchers.Main) {
                        publish(MapObjectEditStore.Label.OnDeleted)
                    }
                }
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetField(
            val field: EditMapObjectField,
        ) : Message

        data class SetTitle(val value: ValidatedField<FieldError>) : Message
        data class SetDescription(val value: ValidatedField<FieldError>) : Message
        data class SetTags(val field: TagsField) : Message

        data object Loading : Message

    }

    override fun getSavedState(): MapObjectEditStore.SavedState {
        return state.toSavedState()
    }
}

private fun MapObjectEditStore.SavedState.toState(
    formatAndValidateTitleUseCase: FormatAndValidateTitleUseCase,
    formatAndValidateDescriptionUseCase: FormatAndValidateDescriptionUseCase,
): MapObjectEditStore.State {
    return MapObjectEditStore.State(
        isLoading = input == null,
        id = id,
        field = input?.toState(
            id = id,
            formatAndValidateTitleUseCase = formatAndValidateTitleUseCase,
            formatAndValidateDescriptionUseCase = formatAndValidateDescriptionUseCase
        )
    )
}

private fun MapObjectEditStore.SavedState.Input.toState(
    id: Long,
    formatAndValidateTitleUseCase: FormatAndValidateTitleUseCase,
    formatAndValidateDescriptionUseCase: FormatAndValidateDescriptionUseCase,
): EditMapObjectField {
    return EditMapObjectField(
        id = id,
        category = category,
        title = formatAndValidateTitleUseCase(title),
        description = formatAndValidateDescriptionUseCase(description),
        tags = TagsField(
            value = tag,
            isInputAvailable = tags.size < MAX_TAGS,
            tags = TagsField.Tags(
                tags = tags.toPersistentList(),
                maxTags = MAX_TAGS,
            ),
            suggestion = FieldSuggestion(false, persistentListOf()),
        )
    )
}

private fun EditMapObjectField.toSavedState(): MapObjectEditStore.SavedState.Input {
    return MapObjectEditStore.SavedState.Input(
        title = title.value,
        description = description.value,
        tag = tags.value,
        tags = tags.tags.tags.toList(),
        category = category,
    )
}

private fun MapObjectEditStore.State.toSavedState(): MapObjectEditStore.SavedState {
    return MapObjectEditStore.SavedState(
        id = id,
        input = field?.toSavedState(),
    )
}


private const val MAX_TAGS = 5