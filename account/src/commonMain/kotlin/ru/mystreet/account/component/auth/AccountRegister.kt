package ru.mystreet.account.component.auth

import com.arkivanov.decompose.value.Value
import ru.mystreet.account.component.domain.entity.RegisterField

interface AccountRegister {

    val isLoading: Value<Boolean>

    val isContinueAvailable: Value<Boolean>

    val loginField: Value<RegisterField>

    fun onNameInput(value: String)

    fun onLoginInput(value: String)

    fun onEmailInput(value: String)

    fun onPasswordInput(value: String)

    fun onPasswordRepeatInput(value: String)

    fun onContinue()

    fun onLogin()

}