package ru.mystreet.map.component.editmap

import com.arkivanov.decompose.value.Value
import ru.mystreet.map.domain.entity.MapObjectCategory

interface EditMap {

    val bottomBar: EditMapBottomBar

    val isEnabled: Value<Boolean>

    fun onToggleEnabled(categories: List<MapObjectCategory>)

}