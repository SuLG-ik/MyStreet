package ru.mystreet.account.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.account.component.auth.AccountLogin
import ru.mystreet.account.component.domain.entity.LoginField
import ru.mystreet.uikit.UIKitOutlineTextField

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
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        UIKitOutlineTextField(
            title = "Логин или почта",
            value = field.login,
            onValueChange = onLoginInput,
            modifier = Modifier.fillMaxWidth(),
        )
        UIKitOutlineTextField(
            title = "Пароль",
            value = field.password,
            onValueChange = onPasswordInput,
            modifier = Modifier.fillMaxWidth(),
        )
        FilledTonalButton(onClick = onContinue, enabled = isContinueAvailable) {
            AnimatedVisibility(visible = isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text("Продолжить")
        }
    }
}