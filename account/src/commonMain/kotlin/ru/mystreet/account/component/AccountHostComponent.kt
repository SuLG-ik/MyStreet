package ru.mystreet.account.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mystreet.account.component.auth.AccountAuthHost
import ru.mystreet.account.component.profile.AccountProfileHost
import ru.mystreet.app.feature.dialogs.component.modal.ModalDialogComponent

interface AccountHostComponent: ModalDialogComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Auth(val component: AccountAuthHost) : Child
        data object Loading : Child
        class Profile(val component: AccountProfileHost) : Child
    }

}