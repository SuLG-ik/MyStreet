package ru.mystreet.account.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.account.component.auth.AccountLogin
import ru.mystreet.account.domain.entity.FieldError
import ru.mystreet.account.domain.entity.LoginField
import ru.mystreet.uikit.AppIcon
import ru.mystreet.uikit.KeyboardActionsDone
import ru.mystreet.uikit.KeyboardActionsNext
import ru.mystreet.uikit.KeyboardOptionsDone
import ru.mystreet.uikit.KeyboardOptionsNext
import ru.mystreet.uikit.UIKitValidatedOutlinedPasswordField
import ru.mystreet.uikit.UIKitValidatedOutlinedTextField
import ru.mystreet.uikit.paddingIme

@Composable
fun AccountLoginUI(
    component: AccountLogin,
    modifier: Modifier = Modifier,
) {
    val isLoading by component.isLoading.subscribeAsState()
    val isContinueAvailable by component.isContinueAvailable.subscribeAsState()
    val field by component.loginField.subscribeAsState()
    val isCredentialsIncorrect by component.isCredentialsIncorrect.subscribeAsState()
    AccountLoginScreen(
        isLoading = isLoading,
        isContinueAvailable = isContinueAvailable,
        field = field,
        isCredentialsIncorrect = isCredentialsIncorrect,
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
    isCredentialsIncorrect: Boolean,
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
        modifier = modifier.paddingIme()
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(300.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            AppIcon(
                modifier = Modifier.size(200.dp)
            )
            AnimatedVisibility(isCredentialsIncorrect) {
                Text(
                    "Неправильный логин или пароль",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                )
            }
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
        UIKitValidatedOutlinedTextField(
            title = "Логин",
            value = field.login,
            onValueChange = onLoginInput,
            singleLine = true,
            errorText = {
                ErrorText(it.formatLoginError())
            },
            keyboardActions = KeyboardActionsNext,
            keyboardOptions = KeyboardOptionsNext,
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
            keyboardActions = KeyboardActionsDone,
            keyboardOptions = KeyboardOptionsDone.copy(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun ErrorText(text: String?, modifier: Modifier = Modifier) {
    if (text != null) {
        Text(text, modifier = modifier)
    }
}

@Composable
fun FieldError.formatLoginError(): String {
    return when (this) {
        FieldError.IllegalInput -> "Неверный ввод"
        is FieldError.IllegalLength -> "Длина должна быть не менее $minSize"
        FieldError.EmptyField -> "Поле не должно быть пустым"
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