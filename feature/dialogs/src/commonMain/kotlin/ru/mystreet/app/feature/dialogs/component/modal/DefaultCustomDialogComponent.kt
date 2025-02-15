package ru.mystreet.app.feature.dialogs.component.modal

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.mystreet.app.feature.dialogs.presentation.DialogCustomStore
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getStore
import ru.mystreet.core.component.values

class DefaultCustomDialogComponent(
    componentContext: DIComponentContext,
    private val onHide: () -> Unit = {},
    private val onShow: () -> Unit = {},
) : CustomDialogComponent, AppComponentContext(componentContext) {

    private val store: DialogCustomStore = getStore()

    override val dialogState: Value<CustomDialogComponent.State> = store.values(this).map { it.toState() }

    override fun show() {
        store.accept(DialogCustomStore.Intent.Show)
        onShow()
    }

    override fun hide() {
        store.accept(DialogCustomStore.Intent.Hide)
        onHide()
    }

    override fun onDismiss() {
        hide()
    }
}

private fun DialogCustomStore.State.toState(): CustomDialogComponent.State {
    return CustomDialogComponent.State(
        isVisible = isVisible,
    )
}
