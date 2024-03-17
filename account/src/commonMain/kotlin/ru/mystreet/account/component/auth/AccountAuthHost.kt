package ru.mystreet.account.component.auth

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface AccountAuthHost {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Login(val component: AccountLogin) : Child
        class Register(val component: AccountRegister) : Child
    }

}