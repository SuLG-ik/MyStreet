package ru.mystreet.account.presentation

import kotlinx.serialization.Serializable
import ru.mystreet.account.domain.entity.RegisterField
import ru.mystreet.core.component.SavedStateStore

interface AccountRegisterStore :
    SavedStateStore<AccountRegisterStore.Intent, AccountRegisterStore.State, AccountRegisterStore.Label, AccountRegisterStore.SavedState> {

    @Serializable
    data class SavedState(
        val name: String = "",
        val email: String = "",
        val username: String = "",
        val password: String = "",
        val passwordRepeat: String = "",
    )

    sealed interface Intent {
        data class EmailInput(val value: String): Intent
        data class NameInput(val value: String): Intent
        data class UsernameInput(val value: String) : Intent
        data class PasswordInput(val value: String) : Intent
        data class PasswordRepeatInput(val value: String) : Intent
        data object Continue : Intent
    }

    data class State(
        val isLoading: Boolean = false,
        val isContinueAvailable: Boolean = false,
        val field: RegisterField = RegisterField(),
    )

    sealed interface Label {
        data class RegisterSuccess(val username: String) : Label
    }

}