package ru.mystreet.map.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface EditMapBottomBar {

    val childStack: Value<ChildStack<*, Child>>

    val isVisible: Value<Boolean>

    fun onContinue()

    sealed interface Child {

        data class SelectCategory(val component: EditMapSelectCategory): Child

    }

}