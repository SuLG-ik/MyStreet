package ru.mystreet.app.feature.dialogs.ui

import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.app.feature.dialogs.component.DialogComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogUI(
    component: DialogComponent,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    CustomDialogUI(component) {
        BasicAlertDialog(
            onDismissRequest = component::onDismiss,
            modifier = modifier,
            content = content,
        )
    }
}
