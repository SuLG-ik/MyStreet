package ru.mystreet.root.component

import coil3.ImageLoader
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface AppRoot {

    val imageLoader: ImageLoader

    val isInitializing: Value<Boolean>

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {

        data object Initializing : Child

        data class MapHost(val component: ru.mystreet.map.root.component.MapHost) : Child

    }

}
