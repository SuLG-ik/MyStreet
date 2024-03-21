package ru.mystreet.account.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.account.component.AccountHost
import ru.mystreet.account.ui.profile.AccountProfileHostUI
import ru.mystreet.uikit.UIKitChildren

@Composable
fun AccountHostUI(
    component: AccountHost,
    modifier: Modifier = Modifier,
) {
    UIKitChildren(component.childStack) {
        AccountHostNavHost(it.instance, modifier = modifier)
    }
}

@Composable
fun AccountHostNavHost(
    child: AccountHost.Child,
    modifier: Modifier,
) {
    when (child) {
        is AccountHost.Child.Auth -> AccountAuthHostUI(child.component, modifier)
        AccountHost.Child.Loading -> TODO()
        is AccountHost.Child.Profile -> AccountProfileHostUI(child.component, modifier)
    }
}