package ru.mystreet.account.component.auth

import com.arkivanov.decompose.value.Value
import ru.mystreet.account.component.domain.entity.LoginField

interface AccountLogin {

    val isLoading: Value<Boolean>

    val isContinueAvailable: Value<Boolean>

    val loginField: Value<LoginField>

    fun onLoginInput(value: String)
    fun onPasswordInput(value: String)

    fun onContinue()
}