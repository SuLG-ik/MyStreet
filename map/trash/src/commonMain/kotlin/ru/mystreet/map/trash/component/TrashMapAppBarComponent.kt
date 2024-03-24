package ru.mystreet.map.trash.component

import com.arkivanov.decompose.value.Value
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext

class TrashMapAppBarComponent(
    componentContext: DIComponentContext,
    override val isInEditMode: Value<Boolean>,
    private val onEditModeToggle: () -> Unit,
) :
    AppComponentContext(componentContext), TrashMapAppBar {

    override fun onToggleMode() {
        onEditModeToggle.invoke()
    }

}