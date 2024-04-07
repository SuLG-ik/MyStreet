package ru.mystreet.map.general.component

import com.arkivanov.decompose.value.Value
import ru.mystreet.map.domain.entity.GeneralLayer

interface GeneralMapAppBar {

    val isInEditMode: Value<Boolean>

    fun onEditModeToggle()

}