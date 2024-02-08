package ru.mystreet.map.general.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.domain.GeneralLayer
import ru.mystreet.map.domain.GeneralLayerType

interface LayersConfigRepository {

    val layersConfig: Flow<List<GeneralLayer>>

    suspend fun setLayerEnabled(layer: GeneralLayerType, enabled: Boolean)

}