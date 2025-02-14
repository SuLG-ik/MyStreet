package ru.mystreet.app.feature.dialogs.component.modal

import ru.mystreet.app.feature.dialogs.component.DialogComponent

interface CustomDialogComponent : DialogComponent {

    fun show()

    fun hide()

    data class State(
        override val isVisible: Boolean,
    ) : DialogComponent.State
}
