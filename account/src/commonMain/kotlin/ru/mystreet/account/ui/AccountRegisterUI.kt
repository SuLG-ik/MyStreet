package ru.mystreet.account.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.account.component.auth.AccountRegister
import ru.mystreet.account.domain.entity.RegisterField
import ru.mystreet.uikit.AppIcon
import ru.mystreet.uikit.UIKitValidatedOutlinedPasswordField
import ru.mystreet.uikit.UIKitValidatedOutlinedTextField
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.BackButton
import ru.mystreet.uikit.paddingIme

@Composable
fun AccountRegisterUI(
    component: AccountRegister,
    modifier: Modifier = Modifier,
) {
    val isLoading by component.isLoading.subscribeAsState()
    val isContinueAvailable by component.isContinueAvailable.subscribeAsState()
    val field by component.registerField.subscribeAsState()
    AccountRegisterScreen(
        isLoading = isLoading,
        isContinueAvailable = isContinueAvailable,
        field = field,
        onNameInput = component::onNameInput,
        onLoginInput = component::onUsernameInput,
        onPasswordInput = component::onPasswordInput,
        onPasswordRepeatInput = component::onPasswordRepeatInput,
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
        modifier = modifier.paddingIme()
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
            Row {
                BackButton(onClick = onLogin)
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
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier,
    ) {
        UIKitValidatedOutlinedTextField(
            title = "Логин",
            value = field.login,
            onValueChange = onLoginInput,
            singleLine = true,
            errorText = {
                ErrorText(it.formatLoginError())
            },
            modifier = Modifier.fillMaxWidth(),
        )
        UIKitValidatedOutlinedTextField(
            title = "Почта",
            value = field.email,
            onValueChange = onEmailInput,
            singleLine = true,
            errorText = {
                ErrorText(it.formatLoginError())
            },
            modifier = Modifier.fillMaxWidth(),
        )
        UIKitValidatedOutlinedTextField(
            title = "Отображаемое имя",
            value = field.name,
            onValueChange = onNameInput,
            singleLine = true,
            errorText = {
                ErrorText(it.formatLoginError())
            },
            modifier = Modifier.fillMaxWidth(),
        )
        UIKitValidatedOutlinedPasswordField(
            title = "Пароль",
            value = field.password,
            onValueChange = onPasswordInput,
            singleLine = true,
            errorText = {
                ErrorText(it.formatLoginError())
            },
            modifier = Modifier.fillMaxWidth(),
        )
        UIKitValidatedOutlinedPasswordField(
            title = "Повторите пароль",
            value = field.passwordRepeat,
            errorText = {
                ErrorText(it.formatLoginError())
            },
            onValueChange = onPasswordRepeatInput,
            singleLine = true,
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

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    OutlinedIconButton(
        onClick = onClick,
        colors = IconButtonDefaults.outlinedIconButtonColors(),
        modifier = modifier,
    ) {
        Icon(
            UIKitIconPack.BackButton,
            contentDescription = null,
            modifier = Modifier.size(with(density) { LocalTextStyle.current.lineHeight.toDp() })
        )
    }
}