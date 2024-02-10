package ru.mystreet.map.component

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ru.mystreet.map.domain.SelectableCategory
import ru.mystreet.map.domain.SelectableCategoryType

class EditMapSelectCategoryComponent : EditMapSelectCategory {
    override val isContinueAvailable: Value<Boolean> = MutableValue(false)
    override val selectedCategory: Value<List<SelectableCategory>> =
        MutableValue(SelectableCategoryType.entries.map {
            SelectableCategory(
                false,
                0,
                it,
            )
        })
}