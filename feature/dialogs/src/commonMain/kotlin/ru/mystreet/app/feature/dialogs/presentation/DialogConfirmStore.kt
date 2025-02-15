package ru.mystreet.app.feature.dialogs.presentation

import com.arkivanov.mvikotlin.core.store.Store

interface DialogConfirmStore :
    Store<DialogConfirmStore.Intent, DialogConfirmStore.State, DialogConfirmStore.Label> {

    sealed interface Intent {
        data object Confirm : Intent
        data object Cancel : Intent
        data object Show : Intent
    }

    data class State(
        val isVisible: Boolean = false,
    )

    sealed interface Label {
        data object OnConfirm : Label
        data object OnCancel : Label
    }
}
