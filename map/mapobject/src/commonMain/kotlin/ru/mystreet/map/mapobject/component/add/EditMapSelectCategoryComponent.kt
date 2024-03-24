package ru.mystreet.map.component.add

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getSavedStateStore
import ru.mystreet.core.component.values
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.SelectableCategory
import ru.mystreet.map.presentation.add.EditMapSelectCategoryStore

class EditMapSelectCategoryComponent(
    componentContext: DIComponentContext,
    categories: List<MapObjectCategory>,
    category: MapObjectCategory?,
    private val onContinue: (MapObjectCategory) -> Unit,
) : AppComponentContext(componentContext), EditMapSelectCategory {

    private val store: EditMapSelectCategoryStore = getSavedStateStore(
        "edit_map_select_category",
        initialSavedState = {
            EditMapSelectCategoryStore.SavedState(
                categories = categories,
                selectedCategoryType = category ?: MapObjectCategory.Bench,
            )
        })

    override val isContinueAvailable: Value<Boolean> = MutableValue(true)

    private val state = store.values(this)

    override val selectedCategories: Value<List<SelectableCategory>> =
        state.map { it.categories }

    override val selectedCategory: Value<MapObjectCategory> =
        state.map { it.selectedCategory }

    override fun onSelect(categoryType: MapObjectCategory) {
        store.accept(EditMapSelectCategoryStore.Intent.SelectCategory(categoryType))
    }

    override fun onContinue() {
        onContinue.invoke(store.state.selectedCategory)
    }

}