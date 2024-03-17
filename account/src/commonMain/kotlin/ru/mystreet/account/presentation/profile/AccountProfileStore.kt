package ru.mystreet.account.presentation.profile

import com.arkivanov.mvikotlin.core.store.Store
import ru.mystreet.account.domain.entity.AccountProfileFull

interface AccountProfileStore :
    Store<AccountProfileStore.Intent, AccountProfileStore.State, AccountProfileStore.Label> {

    sealed interface Intent {

    }

    data class State(
        val isLoading: Boolean = true,
        val account: AccountProfileFull? = null,
    )

    sealed interface Label {
        data object OnNotAuthenticated : Label
    }

}