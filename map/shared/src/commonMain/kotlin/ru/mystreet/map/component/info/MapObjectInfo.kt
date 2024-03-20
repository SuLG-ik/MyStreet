package ru.mystreet.map.component.info

import com.arkivanov.decompose.value.Value
import ru.mystreet.core.component.ValueContainer
import ru.mystreet.map.domain.entity.MapObject

interface MapObjectInfo {

    val isLoading: Value<Boolean>

    val mapObjectInfo: Value<ValueContainer<MapObject?>>

    fun onImagePicker()

    fun onFavourite(value: Boolean)

    fun onEdit()

}