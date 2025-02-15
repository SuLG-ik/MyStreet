package ru.mystreet.app.feature.dialogs.component.confirm

import ru.mystreet.app.feature.dialogs.component.DialogComponent

interface DialogConfirmComponent : DialogComponent {

    fun show()

    fun onCancel() {
        onDismiss()
    }

    fun onConfirm()

    data class State(
        override val isVisible: Boolean,
    ) : DialogComponent.State
}
