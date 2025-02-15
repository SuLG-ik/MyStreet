package ru.mystreet.account.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.account.component.AccountHostComponent
import ru.mystreet.account.ui.profile.AccountProfileHostUI
import ru.mystreet.app.feature.dialogs.ui.ModalDialogUI
import ru.mystreet.uikit.UIKitChildren

@Composable
fun AccountHostUI(
    component: AccountHostComponent,
    modifier: Modifier = Modifier,
) {
    ModalDialogUI(component) {
        UIKitChildren(component.childStack) {
            AccountHostNavHost(it.instance, modifier = modifier)
        }
    }
}

@Composable
fun AccountHostNavHost(
    child: AccountHostComponent.Child,
    modifier: Modifier,
) {
    when (child) {
        is AccountHostComponent.Child.Auth -> AccountAuthHostUI(child.component, modifier)
        AccountHostComponent.Child.Loading -> TODO()
        is AccountHostComponent.Child.Profile -> AccountProfileHostUI(child.component, modifier)
    }
}