package ru.mystreet.app.feature.dialogs.component.modal

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.mystreet.core.component.DIComponentContext

class DefaultModalDialogComponent(
    componentContext: DIComponentContext,
    skipPartiallyExpanded: Boolean = false,
    onHide: () -> Unit = {},
    onShow: () -> Unit = {},
) : ModalDialogComponent,
    CustomDialogComponent {

    private val customDialog = DefaultCustomDialogComponent(
        componentContext = componentContext,
        onHide = onHide,
        onShow = onShow
    )

    override fun show() {
        customDialog.show()
    }

    override fun hide() {
        customDialog.hide()
    }

    override val dialogState: Value<ModalDialogComponent.State> = customDialog.dialogState.map {
        State(
            isVisible = it.isVisible,
            skipPartiallyExpanded = skipPartiallyExpanded
        )
    }

    override fun onDismiss() {
        customDialog.onDismiss()
    }

    data class State(
        override val isVisible: Boolean,
        override val skipPartiallyExpanded: Boolean,
    ) : ModalDialogComponent.State
}
