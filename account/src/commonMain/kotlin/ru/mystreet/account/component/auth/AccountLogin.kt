package ru.mystreet.account.component.auth

import com.arkivanov.decompose.value.Value
import ru.mystreet.account.domain.entity.LoginField

interface AccountLogin {

    val isLoading: Value<Boolean>

    val isContinueAvailable: Value<Boolean>

    val loginField: Value<LoginField>

    val isCredentialsIncorrect: Value<Boolean>

    fun onLoginInput(value: String)
    fun onPasswordInput(value: String)

    fun onContinue()
    fun onRegister()
    fun onRestorePassword()
}