package ru.mystreet.map.map.component

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow
import ru.mystreet.core.component.ValueContainer
import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.geomety.Point

interface MapObjects {

    fun onBind()

    fun onUnbind()

    fun setCategories(categories: List<MapObjectCategory>)

    val userLocationPoint: Point?

}