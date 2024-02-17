package ru.mystreet.map.component

import com.arkivanov.decompose.value.Value
import ru.mystreet.map.domain.entity.AddMapObjectField

interface EditMapNewObjectInfo {

    val field: Value<AddMapObjectField>

    fun onTitleInput(value: String)

    fun onDescriptionInput(value: String)

    fun onContinue()
}