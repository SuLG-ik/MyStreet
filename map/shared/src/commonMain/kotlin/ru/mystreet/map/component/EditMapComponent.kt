package ru.mystreet.map.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.mystreet.app.MapController
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildContext
import ru.mystreet.core.component.getSavedStateStore
import ru.mystreet.core.component.values
import ru.mystreet.map.presentation.EditMapStore

class EditMapComponent(
    componentContext: DIComponentContext,
    mapController: MapController,
) : AppComponentContext(componentContext), EditMap {

    private val store: EditMapStore =
        getSavedStateStore(
            key = "edit_map_store",
            initialSavedState = { EditMapStore.SavedState(false) },
        )

    private val state = store.values(this)

    override val isEnabled: Value<Boolean> = state.map { it.isEnabled }

    override val bottomBar: EditMapBottomBar =
        EditMapBottomBarComponent(
            componentContext = diChildContext(key = "edit_map_bottom_bar"),
            isVisible = isEnabled,
            currentTarget = mapController.currentTarget,
            onObjectAdded = this::onToggleDisabled,
        )

    override fun onToggleEnabled() {
        store.accept(EditMapStore.Intent.Toggle(!state.value.isEnabled))
    }


    private fun onToggleDisabled() {
        store.accept(EditMapStore.Intent.Toggle(false))
    }

}