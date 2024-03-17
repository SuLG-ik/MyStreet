package ru.mystreet.account.presentation.profile

import com.arkivanov.mvikotlin.core.store.Store

interface AccountProfileSettingStore :
    Store<AccountProfileSettingStore.Intent, AccountProfileSettingStore.State, AccountProfileSettingStore.Label> {

    sealed interface Intent {
        data object LogOut : Intent
    }

    data class State(
        val isLoading: Boolean,
    )

    sealed interface Label {

        data object OnLogOut : Label

    }
}