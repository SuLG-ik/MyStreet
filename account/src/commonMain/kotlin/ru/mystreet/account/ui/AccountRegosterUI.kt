package ru.mystreet.account.ui

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import ru.mystreet.account.component.auth.AccountRegister
import ru.mystreet.account.component.domain.entity.RegisterField
import ru.mystreet.uikit.AppIcon
import ru.mystreet.uikit.UIKitOutlineTextField

@Composable
fun AccountRegisterUI(
    component: AccountRegister,
    modifier: Modifier = Modifier,
) {
    val isLoading by component.isLoading.subscribeAsState()
    val isContinueAvailable by component.isContinueAvailable.subscribeAsState()
    val field by component.loginField.subscribeAsState()
    AccountRegisterScreen(
        isLoading = isLoading,
        isContinueAvailable = isContinueAvailable,
        field = field,
        onNameInput = component::onNameInput,
        onLoginInput = component::onLoginInput,
        onPasswordInput = component::onPasswordInput,
        onPasswordRepeatInput = component::onPasswordInput,
        onEmailInput = component::onEmailInput,
        onContinue = component::onContinue,
        onLogin = component::onLogin,
        modifier = modifier,
    )
}

@Composable
fun AccountRegisterScreen(
    isLoading: Boolean,
    isContinueAvailable: Boolean,
    field: RegisterField,
    onNameInput: (String) -> Unit,
    onLoginInput: (String) -> Unit,
    onEmailInput: (String) -> Unit,
    onPasswordInput: (String) -> Unit,
    onPasswordRepeatInput: (String) -> Unit,
    onContinue: () -> Unit,
    onLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(WindowInsets.ime.asPaddingValues())
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(300.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            AppIcon(
                modifier = Modifier.sizeIn(maxWidth = 200.dp, maxHeight = 200.dp)
            )
            AccountRegisterFields(
                field = field,
                onNameInput = onNameInput,
                onLoginInput = onLoginInput,
                onEmailInput = onEmailInput,
                onPasswordInput = onPasswordInput,
                onPasswordRepeatInput = onPasswordRepeatInput,
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
                Text("Регистрация")
            }
            NotRegisterMenu(
                onLogin = onLogin,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun AccountRegisterFields(
    field: RegisterField,
    onNameInput: (String) -> Unit,
    onLoginInput: (String) -> Unit,
    onEmailInput: (String) -> Unit,
    onPasswordInput: (String) -> Unit,
    onPasswordRepeatInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        UIKitOutlineTextField(
            title = "Имя",
            value = field.name,
            onValueChange = onNameInput,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        UIKitOutlineTextField(
            title = "Псевдоним",
            value = field.login,
            onValueChange = onLoginInput,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        UIKitOutlineTextField(
            title = "Почта",
            value = field.email,
            onValueChange = onEmailInput,
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
        UIKitOutlineTextField(
            title = "Повторите пароль",
            value = field.passwordRepeat,
            onValueChange = onPasswordRepeatInput,
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun NotRegisterMenu(
    onLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            "Уже есть аккаунт?",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onLogin
            )
        )
    }
}