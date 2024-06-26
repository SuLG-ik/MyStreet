package ru.mystreet.map.mapobject.component.info

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mystreet.core.component.RefreshableChild
import ru.mystreet.imagepicker.component.ImagePicker
import ru.mystreet.map.component.edit.MapObjectEdit

interface MapObjectInfoHost {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Info(override val component: MapObjectInfo) : Child, RefreshableChild
        data class AddImage(val component: ImagePicker) : Child
        data class Edit(val component: MapObjectEdit) : Child
        data class AddReview(val component: MapObjectReviewAdd) : Child
    }

}