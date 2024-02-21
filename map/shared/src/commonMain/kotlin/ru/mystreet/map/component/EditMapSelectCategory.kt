package ru.mystreet.map.component

import com.arkivanov.decompose.value.Value
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.SelectableCategory

interface EditMapSelectCategory {

    val isContinueAvailable: Value<Boolean>

    val selectedCategories: Value<List<SelectableCategory>>

    val selectedCategory: Value<MapObjectCategory>
    fun onSelect(categoryType: MapObjectCategory)

    fun onContinue()

}