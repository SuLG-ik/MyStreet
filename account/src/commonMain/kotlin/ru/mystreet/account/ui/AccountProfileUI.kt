package ru.mystreet.account.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.account.component.profile.AccountProfileHost
import ru.mystreet.account.domain.entity.AccountProfileFull

@Composable
fun AccountProfileUI(
    component: AccountProfileHost,
    modifier: Modifier = Modifier
) {
    val account by component.account.subscribeAsState()
    account.value?.let {
        AccountProfileScreen(
            it,
            modifier = modifier
        )
    }
}
