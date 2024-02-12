package ru.mystreet.map.general.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.mystreet.map.domain.entity.GeneralLayer
import ru.mystreet.map.domain.entity.GeneralLayerType

interface LayersConfigStore :
    Store<LayersConfigStore.Intent, LayersConfigStore.State, LayersConfigStore.Label> {

    sealed interface Intent {
        data class UpdateLayerEnabled(val layer: GeneralLayerType, val isEnabled: Boolean) : Intent
    }

    data class State(
        val layers: List<GeneralLayer> = emptyList(),
    )

    sealed interface Label

}