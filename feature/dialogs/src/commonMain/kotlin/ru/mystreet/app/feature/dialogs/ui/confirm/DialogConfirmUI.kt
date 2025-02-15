package ru.mystreet.app.feature.dialogs.ui.confirm

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.stringResource
import ru.mystreet.app.feature.dialogs.component.confirm.DialogConfirmComponent
import ru.mystreet.app.feature.dialogs.ui.AlertDialogUI
import ru.mystreet.feature.dialogs.Res
import ru.mystreet.feature.dialogs.dialog_confirm_cancel
import ru.mystreet.feature.dialogs.dialog_confirm_confirm
import ru.mystreet.feature.dialogs.dialog_confirm_title

data class DialogConfirmResources(
    val title: String,
    val text: String?,
    val confirm: String,
    val cancel: String?,
    val titleAlign: TextAlign?,
    val reverseActions: Boolean,
)

@Composable
fun defaultDialogConfirmResources(
    title: String = stringResource(Res.string.dialog_confirm_title),
    text: String? = null,
    confirm: String = stringResource(Res.string.dialog_confirm_confirm),
    cancel: String? = stringResource(Res.string.dialog_confirm_cancel),
    titleAlign: TextAlign? = null,
    reverseActions: Boolean = false,
): DialogConfirmResources {
    return DialogConfirmResources(
        title = title,
        text = text,
        confirm = confirm,
        cancel = cancel,
        titleAlign = titleAlign,
        reverseActions = reverseActions,
    )
}

@Composable
fun DialogConfirmUI(
    component: DialogConfirmComponent,
    modifier: Modifier = Modifier,
    resource: DialogConfirmResources = defaultDialogConfirmResources(),
) {
    AlertDialogUI(
        component = component,
    ) {
        DialogConfirmScreen(
            state = resource.toUI(),
            onCancel = component::onCancel,
            onConfirm = component::onConfirm,
            modifier = modifier,
        )
    }
}

private fun DialogConfirmResources.toUI(): DialogConfirmScreenState {
    return DialogConfirmScreenState(
        title = title,
        text = text,
        confirm = confirm,
        cancel = cancel,
        titleAlign = titleAlign,
        reverseActions = reverseActions,
    )
}
