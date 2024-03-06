package ru.mystreet.account.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.account.component.auth.AccountLogin
import ru.mystreet.account.component.domain.entity.LoginField
import ru.mystreet.uikit.AppIcon
import ru.mystreet.uikit.MR
import ru.mystreet.uikit.UIKitOutlineTextField
import ru.mystreet.uikit.iconpack.UIKitIconPack

@Composable
fun AccountLoginUI(
    component: AccountLogin,
    modifier: Modifier = Modifier,
) {
    val isLoading by component.isLoading.subscribeAsState()
    val isContinueAvailable by component.isContinueAvailable.subscribeAsState()
    val field by component.loginField.subscribeAsState()
    AccountLoginScreen(
        isLoading = isLoading,
        isContinueAvailable = isContinueAvailable,
        field = field,
        onLoginInput = component::onLoginInput,
        onPasswordInput = component::onPasswordInput,
        onContinue = component::onContinue,
        onRestorePassword = component::onRestorePassword,
        onRegister = component::onRegister,
        modifier = modifier,
    )
}

@Composable
fun AccountLoginScreen(
    isLoading: Boolean,
    isContinueAvailable: Boolean,
    field: LoginField,
    onLoginInput: (String) -> Unit,
    onPasswordInput: (String) -> Unit,
    onContinue: () -> Unit,
    onRegister: () -> Unit,
    onRestorePassword: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(WindowInsets.ime.asPaddingValues()),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(300.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            AppIcon(
                modifier = Modifier.size(200.dp)
            )
            AccountLoginFields(
                field = field,
                onLoginInput = onLoginInput,
                onPasswordInput = onPasswordInput,
                modifier = Modifier.fillMaxWidth(),
            )
            FilledTonalButton(
                onClick = onContinue,
                enabled = isContinueAvailable,
                modifier = Modifier.fillMaxWidth()
            ) {
                AnimatedVisibility(visible = isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text("Продолжить")
            }
            NotLoginMenu(
                onRegister = onRegister,
                onRestorePassword = onRestorePassword,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun AccountLoginFields(
    field: LoginField,
    onLoginInput: (String) -> Unit,
    onPasswordInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        UIKitOutlineTextField(
            title = "Логин или почта",
            value = field.login,
            onValueChange = onLoginInput,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        UIKitOutlineTextField(
            title = "Пароль",
            value = field.password,
            onValueChange = onPasswordInput,
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun NotLoginMenu(
    onRestorePassword: () -> Unit,
    onRegister: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            "Забыли пароль?",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onRestorePassword
            )
        )
        Text(
            "Создать аккаунт",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onRegister,
            )
        )
    }
}