package ru.mystreet.account.component.auth

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.mystreet.account.domain.entity.LoginField
import ru.mystreet.account.presentation.AccountLoginStore
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getSavedStateStore
import ru.mystreet.core.component.values

class AccountLoginComponent(
    componentContext: DIComponentContext,
    private val onRegister: () -> Unit,
    private val onRestorePassword: () -> Unit,
    private val onAuthenticated: () -> Unit,
) : AppComponentContext(componentContext), AccountLogin {

    private val scope = coroutineScope()

    private val store: AccountLoginStore = getSavedStateStore<AccountLoginStore, _>(
        "account_login",
        initialSavedState = { AccountLoginStore.SavedState("", "") },
    )

    init {
        store.labels.onEach {
            when (it) {
                is AccountLoginStore.Label.LoginSuccess -> onAuthenticated()
            }
        }.flowOn(Dispatchers.Main).launchIn(scope)
    }

    private val state = store.values(this)
    override val isLoading: Value<Boolean> = state.map { it.isLoading }
    override val isContinueAvailable: Value<Boolean> = state.map { it.isContinueAvailable }
    override val loginField: Value<LoginField> = state.map { it.field }

    override fun onLoginInput(value: String) {
        store.accept(AccountLoginStore.Intent.LoginInput(value))
    }

    override fun onPasswordInput(value: String) {
        store.accept(AccountLoginStore.Intent.PasswordInput(value))
    }

    override fun onContinue() {
        store.accept(AccountLoginStore.Intent.Continue)
    }

    override fun onRestorePassword() {
        onRestorePassword.invoke()
    }

    override fun onRegister() {
        onRegister.invoke()
    }
}