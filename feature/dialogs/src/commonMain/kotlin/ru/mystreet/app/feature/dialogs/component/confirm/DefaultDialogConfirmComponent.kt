package ru.mystreet.app.feature.dialogs.component.confirm

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import kotlinx.coroutines.launch
import ru.mystreet.app.feature.dialogs.component.DialogComponent
import ru.mystreet.app.feature.dialogs.presentation.DialogConfirmStore
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getStore
import ru.mystreet.core.component.values

class DefaultDialogConfirmComponent(
    componentContext: DIComponentContext,
    private val onConfirm: () -> Unit,
    private val onCancel: () -> Unit = {},
) : DialogConfirmComponent, AppComponentContext(componentContext) {

    private val store: DialogConfirmStore = getStore()

    private val scope = coroutineScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    DialogConfirmStore.Label.OnCancel -> onCancel.invoke()
                    DialogConfirmStore.Label.OnConfirm -> onConfirm.invoke()
                }
            }
        }
    }

    override val dialogState: Value<DialogComponent.State> =
        store.values(this).map { it.toState() }

    override fun show() {
        store.accept(DialogConfirmStore.Intent.Show)
    }

    override fun onConfirm() {
        store.accept(DialogConfirmStore.Intent.Confirm)
    }

    override fun onDismiss() {
        store.accept(DialogConfirmStore.Intent.Cancel)
    }
}

private fun DialogConfirmStore.State.toState(): DialogConfirmComponent.State {
    return DialogConfirmComponent.State(isVisible = isVisible)
}
