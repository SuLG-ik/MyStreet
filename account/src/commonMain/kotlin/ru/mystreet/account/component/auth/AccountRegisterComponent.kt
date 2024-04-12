package ru.mystreet.account.component.auth

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.rx.Observer
import ru.mystreet.account.domain.entity.RegisterField
import ru.mystreet.account.presentation.AccountRegisterStore
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getSavedStateStore
import ru.mystreet.core.component.values

class AccountRegisterComponent(
    componentContext: DIComponentContext,
    private val onLogin: () -> Unit,
    private val onRegistered: () -> Unit,
) : AppComponentContext(componentContext), AccountRegister {

    private val store: AccountRegisterStore = getSavedStateStore(
        key = "account_register",
        initialSavedState = { AccountRegisterStore.SavedState() }
    )

    init {
        val disposable = store.labels(object : Observer<AccountRegisterStore.Label> {
            override fun onComplete() {
            }

            override fun onNext(value: AccountRegisterStore.Label) {
                when (value) {
                    is AccountRegisterStore.Label.RegisterSuccess -> onRegistered()
                }
            }
        })
        lifecycle.doOnDestroy(disposable::dispose)
    }

    private val state = store.values(this)
    override val isLoading: Value<Boolean> = state.map { it.isLoading }
    override val isContinueAvailable: Value<Boolean> = state.map { it.isContinueAvailable }
    override val registerField: Value<RegisterField> = state.map { it.field }

    override fun onNameInput(value: String) {
        store.accept(AccountRegisterStore.Intent.LoginInput(value))
    }

    override fun onUsernameInput(value: String) {
        store.accept(AccountRegisterStore.Intent.UsernameInput(value))
    }

    override fun onEmailInput(value: String) {
        store.accept(AccountRegisterStore.Intent.EmailInput(value))
    }

    override fun onPasswordInput(value: String) {
        store.accept(AccountRegisterStore.Intent.PasswordInput(value))
    }

    override fun onPasswordRepeatInput(value: String) {
        store.accept(AccountRegisterStore.Intent.PasswordRepeatInput(value))
    }

    override fun onContinue() {
        store.accept(AccountRegisterStore.Intent.Continue)
    }

    override fun onLogin() {
        onLogin.invoke()
    }


}