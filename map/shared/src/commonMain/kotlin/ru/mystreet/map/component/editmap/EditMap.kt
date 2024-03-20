package ru.mystreet.map.component.editmap

import com.arkivanov.decompose.value.Value

interface EditMap {

    val bottomBar: EditMapBottomBar

    val isEnabled: Value<Boolean>

    fun onToggleEnabled()

}