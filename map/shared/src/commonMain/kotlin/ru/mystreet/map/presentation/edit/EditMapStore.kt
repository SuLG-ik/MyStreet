package ru.mystreet.map.presentation.edit

import kotlinx.serialization.Serializable
import ru.mystreet.core.component.SavedStateStore

interface EditMapStore :
    SavedStateStore<EditMapStore.Intent, EditMapStore.State, EditMapStore.Label, EditMapStore.SavedState> {

    @Serializable
    data class SavedState(
        val isEnabled: Boolean,
    )

    sealed interface Intent {
        data class Toggle(val isEnabled: Boolean) : Intent
    }

    data class State(
        val isEnabled: Boolean,
    )

    sealed interface Label

}