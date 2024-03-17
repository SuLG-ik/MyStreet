package ru.mystreet.account.component.profile

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface AccountProfileHost {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Info(
            val component: AccountProfileInfo,
        ) : Child

        class Settings(
            val component: AccountProfileSettings,
        ) : Child
    }

}