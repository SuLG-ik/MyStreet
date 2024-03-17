package ru.mystreet.account.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import ru.mystreet.account.component.profile.AccountProfileHost
import ru.mystreet.uikit.UIKitChildren

@Composable
fun AccountProfileHostUI(
    component: AccountProfileHost,
    modifier: Modifier = Modifier
) {
    UIKitChildren(component.childStack) {
        AccountProfileNavHost(it.instance, modifier)
    }
}

@Composable
fun AccountProfileNavHost(instance: AccountProfileHost.Child, modifier: Modifier) {
    when (instance) {
        is AccountProfileHost.Child.Info -> AccountProfileInfoUI(
            component = instance.component,
            modifier = modifier
        )

        is AccountProfileHost.Child.Settings -> AccountProfileSettingUI(
            component = instance.component,
            modifier = modifier
        )
    }
}
