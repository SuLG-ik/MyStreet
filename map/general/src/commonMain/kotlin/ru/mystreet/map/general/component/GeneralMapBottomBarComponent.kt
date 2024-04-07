package ru.mystreet.map.general.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getStore
import ru.mystreet.core.component.values
import ru.mystreet.map.domain.entity.GeneralLayer
import ru.mystreet.map.domain.entity.GeneralSelectedMode
import ru.mystreet.map.general.presentation.LayersConfigStore

class GeneralMapBottomBarComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), GeneralMapBottomBar {

    private val store: LayersConfigStore = getStore()

    private val state = store.values(this)

    override val layers: Value<List<GeneralLayer>> = state.map { it.layers }

    override fun onGeneralLayerToggle(layer: GeneralLayer) {
        store.accept(LayersConfigStore.Intent.UpdateLayerEnabled(layer.type, !layer.isEnabled))
    }

    override val selectedMode: Value<GeneralSelectedMode> = state.map { it.mode }

    override fun onModeToggle(mode: GeneralSelectedMode) {
        store.accept(LayersConfigStore.Intent.ToggleMode(mode))
    }

}