package ru.mystreet.map.general.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getStore
import ru.mystreet.core.component.values
import ru.mystreet.map.domain.entity.GeneralLayer
import ru.mystreet.map.general.presentation.LayersConfigStore

class GeneralMapAppBarComponent(
    componentContext: DIComponentContext,
    override val isInEditMode: Value<Boolean>,
    private val onEditModeToggle: () -> Unit,
) :
    AppComponentContext(componentContext), GeneralMapAppBar {

    private val store: LayersConfigStore = getStore()

    override val layers: Value<List<GeneralLayer>> = store.values(this).map { it.layers }

    override fun onLayerToggle(layer: GeneralLayer) {
        store.accept(LayersConfigStore.Intent.UpdateLayerEnabled(layer.type, !layer.isEnabled))
    }

    override fun onEditModeToggle() {
        onEditModeToggle.invoke()
    }
}