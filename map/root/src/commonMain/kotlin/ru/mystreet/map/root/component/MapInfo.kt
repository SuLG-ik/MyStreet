package ru.mystreet.map.root.component

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value

interface MapInfo {

    val isExpanded: Value<Boolean>

    val childSlot: Value<ChildSlot<*, Child>>

    fun onShowMapObjectInfo(id: Long)

    sealed interface Child {
        class MapObjectInfo(val component: ru.mystreet.map.component.MapObjectInfo) : Child
    }

    fun onDismiss()
}