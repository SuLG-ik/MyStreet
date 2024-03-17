package ru.mystreet.map.component

import com.arkivanov.decompose.value.Value

interface EditMap {

    val bottomBar: EditMapBottomBar

    val isEnabled: Value<Boolean>

    fun onToggleEnabled()

}