package ru.mystreet.app.feature.dialogs.component.modal

import com.arkivanov.decompose.value.Value
import ru.mystreet.app.feature.dialogs.component.DialogComponent

interface ModalDialogComponent : CustomDialogComponent {

    override val state: Value<State>

    interface State : DialogComponent.State {
        val skipPartiallyExpanded: Boolean
    }
}
