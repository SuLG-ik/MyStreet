package ru.mystreet.map.trash.component

import com.arkivanov.decompose.value.Value

interface TrashMapAppBar {

    val isInEditMode: Value<Boolean>

    fun onToggleMode()

}