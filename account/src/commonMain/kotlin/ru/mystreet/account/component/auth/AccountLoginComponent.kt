package ru.mystreet.account.component.auth

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ru.mystreet.account.component.domain.entity.LoginField
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext

class AccountLoginComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), AccountLogin {
    override val isLoading: Value<Boolean> = MutableValue(false)
    override val isContinueAvailable: Value<Boolean> = MutableValue(false)
    override val loginField: MutableValue<LoginField> = MutableValue(LoginField("", ""))

    override fun onLoginInput(value: String) {
        loginField.value = loginField.value.copy(login = value)
    }

    override fun onPasswordInput(value: String) {
        loginField.value = loginField.value.copy(password = value)
    }

    override fun onContinue() {

    }
}