package ru.mystreet.app.feature.dialogs.presentation

import com.arkivanov.mvikotlin.core.store.Store

interface DialogCustomStore :
    Store<DialogCustomStore.Intent, DialogCustomStore.State, DialogCustomStore.Label> {

    sealed interface Intent {
        data object Show : Intent
        data object Hide : Intent
    }

    data class State(
        val isVisible: Boolean = false,
    )

    sealed interface Label {
        data object OnShow : Label
        data object OnHide : Label
    }
}
