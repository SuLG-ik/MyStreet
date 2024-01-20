package ru.mystreet.app.context

import com.arkivanov.decompose.ComponentContext

interface ScreenComponentContext : ComponentContext {

    val rootState: State

    data class State(
        val isNavigationShown: Boolean,
        val isMapShown: Boolean,
    )

}