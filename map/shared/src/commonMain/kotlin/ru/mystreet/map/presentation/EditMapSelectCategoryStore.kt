package ru.mystreet.map.presentation

import kotlinx.serialization.Serializable
import ru.mystreet.core.component.SavedStateStore
import ru.mystreet.map.domain.SelectableCategory
import ru.mystreet.map.domain.SelectableCategoryType

interface EditMapSelectCategoryStore :
    SavedStateStore<EditMapSelectCategoryStore.Intent, EditMapSelectCategoryStore.State, EditMapSelectCategoryStore.Label, EditMapSelectCategoryStore.SavedState> {

    @Serializable
    data class SavedState(
        val selectedCategoryType: SelectableCategoryType = SelectableCategoryType.Bench,
    )

    sealed interface Intent {
        data class SelectCategory(val type: SelectableCategoryType) : Intent
    }

    data class State(
        val categories: List<SelectableCategory>,
        val selectedCategory: SelectableCategoryType,
    )

    sealed interface Label

}