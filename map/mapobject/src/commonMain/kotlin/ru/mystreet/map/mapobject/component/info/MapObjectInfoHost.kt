package ru.mystreet.map.component.info

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mystreet.imagepicker.component.ImagePicker
import ru.mystreet.map.component.edit.MapObjectEdit

interface MapObjectInfoHost {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Info(val component: MapObjectInfo): Child
        data class AddImage(val component: ImagePicker): Child
        data class Edit(val component: MapObjectEdit): Child
    }

}