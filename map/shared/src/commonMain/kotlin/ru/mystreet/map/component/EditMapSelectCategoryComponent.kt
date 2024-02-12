package ru.mystreet.map.component

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getSavedStateStore
import ru.mystreet.core.component.values
import ru.mystreet.map.domain.entity.SelectableCategory
import ru.mystreet.map.domain.entity.SelectableCategoryType
import ru.mystreet.map.presentation.EditMapSelectCategoryStore

class EditMapSelectCategoryComponent(
    componentContext: DIComponentContext
) : AppComponentContext(componentContext), EditMapSelectCategory {

    private val store: EditMapSelectCategoryStore = getSavedStateStore(
        "edit_map_select_category",
        initialSavedState = { EditMapSelectCategoryStore.SavedState() })

    override val isContinueAvailable: Value<Boolean> = MutableValue(true)

    override val selectedCategory: Value<List<SelectableCategory>> =
        store.values(this).map { it.categories }

    override fun onSelect(categoryType: SelectableCategoryType) {
        store.accept(EditMapSelectCategoryStore.Intent.SelectCategory(categoryType))
    }

}