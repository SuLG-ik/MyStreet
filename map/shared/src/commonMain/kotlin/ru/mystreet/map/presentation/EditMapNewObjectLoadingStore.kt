package ru.mystreet.map.presentation

import kotlinx.serialization.Serializable
import ru.mystreet.core.component.SavedStateStore
import ru.mystreet.map.domain.entity.AddMapObjectField

interface EditMapNewObjectLoadingStore :
    SavedStateStore<EditMapNewObjectLoadingStore.Intent, EditMapNewObjectLoadingStore.State, EditMapNewObjectLoadingStore.Label, EditMapNewObjectLoadingStore.SavedState> {

    @Serializable
    data class SavedState(
        val field: AddMapObjectField,
    )

    sealed interface Intent {

    }

    data class State(
        val isLoading: Boolean = true,
    )

    sealed interface Label {
        data object LoadingCompleted : Label
    }

}