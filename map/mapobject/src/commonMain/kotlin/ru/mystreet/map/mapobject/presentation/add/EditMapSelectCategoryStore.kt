package ru.mystreet.map.presentation.add

import kotlinx.serialization.Serializable
import ru.mystreet.core.component.SavedStateStore
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.SelectableCategory

interface EditMapSelectCategoryStore :
    SavedStateStore<EditMapSelectCategoryStore.Intent, EditMapSelectCategoryStore.State, EditMapSelectCategoryStore.Label, EditMapSelectCategoryStore.SavedState> {

    @Serializable
    data class SavedState(
        val selectedCategoryType: MapObjectCategory = MapObjectCategory.Bench,
    )

    sealed interface Intent {
        data class SelectCategory(val type: MapObjectCategory) : Intent
    }

    data class State(
        val categories: List<SelectableCategory>,
        val selectedCategory: MapObjectCategory,
    )

    sealed interface Label

}