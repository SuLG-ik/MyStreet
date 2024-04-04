package ru.mystreet.map.presentation.info

import kotlinx.serialization.Serializable
import ru.mystreet.core.component.SavedStateStore
import ru.mystreet.map.domain.entity.MapObject

interface MapObjectInfoStore :
    SavedStateStore<MapObjectInfoStore.Intent, MapObjectInfoStore.State, MapObjectInfoStore.Label, MapObjectInfoStore.SavedState> {

    @Serializable
    data class SavedState(
        val id: Long,
    )

    sealed interface Intent {
        data class SetFavourite(val value: Boolean) : Intent
        data object Refresh : Intent
    }

    data class State(
        val isLoading: Boolean = true,
        val mapObject: MapObject? = null,
    )

    sealed interface Label

}