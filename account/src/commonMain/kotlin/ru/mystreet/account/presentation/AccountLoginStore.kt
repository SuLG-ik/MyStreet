package ru.mystreet.account.presentation

import kotlinx.serialization.Serializable
import ru.mystreet.account.domain.entity.LoginField
import ru.mystreet.core.component.SavedStateStore

interface AccountLoginStore :
    SavedStateStore<AccountLoginStore.Intent, AccountLoginStore.State, AccountLoginStore.Label, AccountLoginStore.SavedState> {
    @Serializable
    data class SavedState(
        val login: String,
        val password: String,
    )

    sealed interface Intent {
        data class LoginInput(val value: String) : Intent
        data class PasswordInput(val value: String) : Intent
        data object Continue : Intent
    }

    data class State(
        val isLoading: Boolean,
        val isContinueAvailable: Boolean,
        val isCredentialsIncorrect: Boolean,
        val field: LoginField
    )

    sealed interface Label {
        data class LoginSuccess(val username: String) : Label
    }

}