package ru.mystreet.app.feature.dialogs.component

import com.arkivanov.decompose.value.Value

interface DialogComponent {

    val dialogState: Value<State>

    fun onDismiss()

    interface State {
        val isVisible: Boolean
    }
}
