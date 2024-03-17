package ru.mystreet.account.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.account.component.profile.AccountProfileInfo

@Composable
fun AccountProfileInfoUI(
    component: AccountProfileInfo,
    modifier: Modifier = Modifier
) {
    val account by component.account.subscribeAsState()
    account.value?.let {
        AccountProfileInfoScreen(
            account = it,
            onSettings = component::onSettings,
            modifier = modifier
        )
    }
}
