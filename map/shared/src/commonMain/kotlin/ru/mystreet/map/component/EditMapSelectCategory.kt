package ru.mystreet.map.component

import com.arkivanov.decompose.value.Value
import ru.mystreet.map.domain.SelectableCategory

interface EditMapSelectCategory {

    val isContinueAvailable: Value<Boolean>

    val selectedCategory: Value<List<SelectableCategory>>

}