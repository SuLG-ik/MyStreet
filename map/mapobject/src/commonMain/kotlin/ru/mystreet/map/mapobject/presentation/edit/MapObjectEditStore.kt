package ru.mystreet.map.presentation.edit

import kotlinx.collections.immutable.PersistentList
import kotlinx.serialization.Serializable
import ru.mystreet.core.component.SavedStateStore
import ru.mystreet.map.domain.entity.EditMapObjectField
import ru.mystreet.map.domain.entity.MapObjectCategory

interface MapObjectEditStore :
    SavedStateStore<MapObjectEditStore.Intent, MapObjectEditStore.State, MapObjectEditStore.Label, MapObjectEditStore.SavedState> {

    @Serializable
    data class SavedState(
        val id: Long,
        val input: Input? = null,
    ) {
        @Serializable
        data class Input(
            val title: String,
            val description: String,
            val category: MapObjectCategory,
            val tag: String,
            val tags: List<String>,
        )
    }

    sealed interface Intent {
        data class TitleInput(val value: String) : Intent
        data class DescriptionInput(val value: String) : Intent
        data class TagInput(val value: String) : Intent
        data class RemoveTag(val value: String) : Intent
        data object AddTag : Intent
        data object Continue : Intent
        data object Delete : Intent
    }

    data class State(
        val isLoading: Boolean,
        val id: Long,
        val field: EditMapObjectField?,
    )

    sealed interface Label {
        data object OnDeleted : Label
        data object OnSaved : Label
    }


}