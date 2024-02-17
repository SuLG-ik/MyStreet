package ru.mystreet.map.presentation

import kotlinx.serialization.Serializable
import ru.mystreet.core.component.SavedStateStore
import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.geomety.Latitude
import ru.mystreet.map.geomety.Longitude
import ru.mystreet.map.geomety.Point

interface EditMapNewObjectInfoStore :
    SavedStateStore<EditMapNewObjectInfoStore.Intent, EditMapNewObjectInfoStore.State, EditMapNewObjectInfoStore.Label, EditMapNewObjectInfoStore.SavedState> {

    @Serializable
    data class SavedState(
        val title: String = "",
        val description: String = "",
        val point: Point,
        val category: MapObjectCategory,
    )

    sealed interface Intent {
        data class TitleInput(val value: String) : Intent
        data class DescriptionInput(val value: String) : Intent
        data class PointInput(val value: Point) : Intent
    }

    data class State(
        val field: AddMapObjectField,
    )

    sealed interface Label

}