package ru.mystreet.map.presentation.add

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable
import ru.mystreet.core.component.SavedStateStore
import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.geomety.Point

interface EditMapNewObjectInfoStore :
    SavedStateStore<EditMapNewObjectInfoStore.Intent, EditMapNewObjectInfoStore.State, EditMapNewObjectInfoStore.Label, EditMapNewObjectInfoStore.SavedState> {

    @Serializable
    data class SavedState(
        val title: String = "",
        val description: String = "",
        val tag: String = "",
        val tags: List<String> = listOf(),
        val point: Point,
        val category: MapObjectCategory,
    )

    sealed interface Intent {
        data class TitleInput(val value: String) : Intent
        data class DescriptionInput(val value: String) : Intent
        data class PointInput(val value: Point) : Intent
        data class TagInput(val value: String) : Intent
        data class RemoveTag(val value: String) : Intent
        data object AddTag : Intent
    }

    data class State(
        val field: AddMapObjectField,
    )

    sealed interface Label

}