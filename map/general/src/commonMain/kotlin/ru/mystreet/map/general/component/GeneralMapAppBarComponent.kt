package ru.mystreet.map.general.component

import com.arkivanov.decompose.value.Value
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext

class GeneralMapAppBarComponent(
    componentContext: DIComponentContext,
    override val isInEditMode: Value<Boolean>,
    private val onEditModeToggle: () -> Unit,
) : AppComponentContext(componentContext), GeneralMapAppBar {

    override fun onEditModeToggle() {
        onEditModeToggle.invoke()
    }

}