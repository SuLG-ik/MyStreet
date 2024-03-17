package ru.mystreet.account.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.mystreet.account.component.auth.AccountAuthHost
import ru.mystreet.uikit.UIKitChildren

@Composable
fun AccountAuthHostUI(
    component: AccountAuthHost,
    modifier: Modifier = Modifier,
) {
    UIKitChildren(component.childStack) {
        AccountAuthHostNavHost(it.instance, modifier)
    }
}

@Composable
fun AccountAuthHostNavHost(
    child: AccountAuthHost.Child,
    modifier: Modifier,
) {
    when (child) {
        is AccountAuthHost.Child.Login -> AccountLoginUI(child.component, modifier)
        is AccountAuthHost.Child.Register -> AccountRegisterUI(child.component, modifier)
    }
}