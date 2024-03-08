package ru.mystreet.account.component.auth

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ru.mystreet.account.component.domain.entity.RegisterField
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext

class AccountRegisterComponent(
    componentContext: DIComponentContext,
    private val onLogin: () -> Unit,
) : AppComponentContext(componentContext), AccountRegister {

    override val isLoading: Value<Boolean> = MutableValue(false)
    override val isContinueAvailable: Value<Boolean> = MutableValue(false)
    override val loginField: Value<RegisterField> = MutableValue(RegisterField())

    override fun onNameInput(value: String) {
    }

    override fun onLoginInput(value: String) {
    }

    override fun onEmailInput(value: String) {
    }

    override fun onPasswordInput(value: String) {
    }

    override fun onPasswordRepeatInput(value: String) {
    }

    override fun onContinue() {
    }

    override fun onLogin() {
        onLogin.invoke()
    }


}