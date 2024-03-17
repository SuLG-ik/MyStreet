package ru.mystreet.account.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mystreet.account.component.profile.AccountProfileSettings
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.BackButton
import ru.mystreet.uikit.tokens.UIKitSizeTokens

@Composable
fun AccountProfileSettingUI(
    component: AccountProfileSettings,
    modifier: Modifier = Modifier,
) {
    AccountProfileScreen(
        onLogOut = component::onLogOut,
        onBack = component::onBack,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountProfileScreen(
    onLogOut: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Настройки")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(
                        onClick = onBack,
                    ) {
                        Icon(
                            UIKitIconPack.BackButton,
                            contentDescription = null,
                            modifier = Modifier.size(UIKitSizeTokens.DefaultIconSize),
                        )
                    }
                }
            )
        },
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it).padding(horizontal = 15.dp).verticalScroll(rememberScrollState())
        ) {
            TextButton(
                onClick = onLogOut,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Выйти")
            }
        }
    }
}