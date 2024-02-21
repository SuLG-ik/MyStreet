package ru.mystreet.root.presentation

import kotlinx.serialization.Serializable
import ru.mystreet.core.component.SavedStateStore
import ru.mystreet.map.domain.entity.MapConfig

interface AppRootInitializingStore :
    SavedStateStore<AppRootInitializingStore.Intent, AppRootInitializingStore.State, AppRootInitializingStore.Label, AppRootInitializingStore.SavedState> {

    @Serializable
    data class SavedState(
        val mapConfig: MapConfig? = null,
    )

    sealed interface Intent

    data class State(
        val isLoading: Boolean = true,
        val mapConfig: MapConfig? = null,
    )

    sealed interface Label {
        data class AppRootInitialized(val mapConfig: MapConfig) : Label
    }

}