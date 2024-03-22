package ru.mystreet.map.component.editmap

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mystreet.map.component.add.EditMapNewObjectInfo
import ru.mystreet.map.component.add.EditMapNewObjectLoading
import ru.mystreet.map.component.add.EditMapSelectCategory

interface EditMapBottomBar {

    val childStack: Value<ChildStack<*, Child>>

    val isVisible: Value<Boolean>

    sealed interface Child {

        data class SelectCategory(val component: EditMapSelectCategory) : Child

        data class EditInfo(val component: EditMapNewObjectInfo) : Child

        data class Loading(val component: EditMapNewObjectLoading) : Child

    }

}