package ru.mystreet.map.mapobject.presentation.info

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mystreet.core.component.onIntentSkipping
import ru.mystreet.map.domain.entity.AddMapObjectReviewField
import ru.mystreet.map.domain.entity.FieldError
import ru.mystreet.map.domain.usecase.AddMapObjectReviewUseCase
import ru.mystreet.uikit.ValidatedField

@OptIn(ExperimentalMviKotlinApi::class)
class MapObjectReviewAddStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    private val savedState: MapObjectReviewAddStore.SavedState,
    addMapObjectReviewUseCase: AddMapObjectReviewUseCase,
) : MapObjectReviewAddStore,
    Store<MapObjectReviewAddStore.Intent, MapObjectReviewAddStore.State, MapObjectReviewAddStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "MapObjectReviewAddStoreImpl",
        initialState = savedState.restore(),
        reducer = {
            when (it) {
                is Message.RatingInput -> copy(
                    isContinueAvailable = it.value > 0,
                    field = field.copy(rating = it.value)
                )

                is Message.AuthorHiddenInput -> copy(field = field.copy(isAuthorHidden = it.value))
                is Message.ContentInput -> copy(field = field.copy(content = it.value))
                is Message.TitleInput -> copy(field = field.copy(title = it.value))
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {

            }
            onIntent<MapObjectReviewAddStore.Intent.RatingInput> {
                dispatch(Message.RatingInput(it.value))
            }
            onIntent<MapObjectReviewAddStore.Intent.AuthorHiddenInput> {
                dispatch(Message.AuthorHiddenInput(it.value))
            }
            onIntent<MapObjectReviewAddStore.Intent.TitleInput> {
                dispatch(Message.TitleInput(ValidatedField(it.value, null)))
            }
            onIntent<MapObjectReviewAddStore.Intent.ContentInput> {
                dispatch(Message.ContentInput(ValidatedField(it.value, null)))
            }
            onIntentSkipping { intent: MapObjectReviewAddStore.Intent ->
                val state = state()
                if (!state.isContinueAvailable)
                    return@onIntentSkipping
                launch {
                    addMapObjectReviewUseCase(savedState.mapObjectId, state.field)
                    withContext(Dispatchers.Main) {
                        publish(MapObjectReviewAddStore.Label.Complete)
                    }
                }
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class RatingInput(val value: Int) : Message
        data class TitleInput(val value: ValidatedField<FieldError>) : Message
        data class ContentInput(val value: ValidatedField<FieldError>) : Message
        data class AuthorHiddenInput(val value: Boolean) : Message
    }

    override fun getSavedState(): MapObjectReviewAddStore.SavedState {
        return MapObjectReviewAddStore.SavedState(
            mapObjectId = savedState.mapObjectId,
            title = state.field.title.value,
            content = state.field.content.value,
            isAuthorHidden = state.field.isAuthorHidden,
            rating = state.field.rating
        )
    }
}

fun MapObjectReviewAddStore.SavedState.restore(): MapObjectReviewAddStore.State {
    return MapObjectReviewAddStore.State(
        isContinueAvailable = rating > 0,
        isLoading = false,
        field = AddMapObjectReviewField(
            title = ValidatedField(title, null),
            content = ValidatedField(content, null),
            rating = rating,
            isAuthorHidden = isAuthorHidden,
        )
    )
}