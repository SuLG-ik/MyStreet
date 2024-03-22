package ru.mystreet.map.component.edit

import com.arkivanov.decompose.value.Value
import ru.mystreet.core.component.ValueContainer
import ru.mystreet.map.domain.entity.EditMapObjectField

interface MapObjectEdit {

    val field: Value<ValueContainer<EditMapObjectField?>>

    fun onTitleInput(value: String)

    fun onDescriptionInput(value: String)

    fun onTagInput(value: String)

    fun onTagRemove(value: String)

    fun onTagAdd()

    fun onContinue()

    fun onBack()

    val isLoading: Value<Boolean>
    fun onDelete()
}