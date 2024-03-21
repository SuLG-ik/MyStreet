package ru.mystreet.core.component

import com.arkivanov.decompose.ComponentContext

fun interface ChildFactory<Context : ComponentContext, Config : Any, Child : Any> {
    fun createChild(config: Config, context: Context): Child
}