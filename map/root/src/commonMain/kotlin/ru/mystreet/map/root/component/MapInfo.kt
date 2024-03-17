package ru.mystreet.map.root.component

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import ru.mystreet.map.component.info.MapObjectInfoHostComponent

interface MapInfo {

    val isExpanded: Value<Boolean>

    val childSlot: Value<ChildSlot<*, Child>>

    fun onShowMapObjectInfo(id: Long)

    sealed interface Child {
        class MapObjectInfo(val component: MapObjectInfoHostComponent) : Child
    }

    fun onDismiss()
}