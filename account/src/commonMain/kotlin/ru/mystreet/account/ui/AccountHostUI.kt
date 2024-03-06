package ru.mystreet.account.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.account.component.AccountHost
import ru.mystreet.uikit.InfoBottomSheetScreen

@Composable
fun AccountHostUI(
    component: AccountHost,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val isExpanded by component.isExpanded.subscribeAsState()
    InfoBottomSheetScreen(
        isExpanded = isExpanded,
        onDismiss = component::onDismiss,
        sheetContent = {
            Children(component.childStack) {
                AccountHostNavHost(it.instance, modifier = Modifier.fillMaxSize())
            }
        },
        modifier = modifier,
        content = content,
    )
}

@Composable
fun AccountHostNavHost(
    child: AccountHost.Child,
    modifier: Modifier,
) {
    when (child) {
        is AccountHost.Child.Auth -> AccountAuthHostUI(child.component, modifier)
        AccountHost.Child.Loading -> TODO()
        is AccountHost.Child.Profile -> TODO()
    }
}