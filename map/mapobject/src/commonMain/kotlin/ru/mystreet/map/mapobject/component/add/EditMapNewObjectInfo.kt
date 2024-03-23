package ru.mystreet.map.component.add

import com.arkivanov.decompose.value.Value
import ru.mystreet.map.domain.entity.AddMapObjectField

interface EditMapNewObjectInfo {

    val field: Value<AddMapObjectField>

    fun onTitleInput(value: String)

    fun onDescriptionInput(value: String)

    fun onTagInput(value: String)

    fun onTagRemove(value: String)

    fun onTagAdd()

    fun onContinue()

    fun onBack()

}