package ru.mystreet.app.feature.dialogs.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.app.feature.dialogs.component.DialogComponent

@Composable
fun CustomDialogUI(
    component: DialogComponent,
    content: @Composable () -> Unit,
) {
    val state by component.state.subscribeAsState()
    if (state.isVisible) {
        content()
    }
}
