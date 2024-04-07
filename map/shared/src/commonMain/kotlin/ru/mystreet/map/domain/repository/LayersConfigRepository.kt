package ru.mystreet.map.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.domain.entity.GeneralLayer
import ru.mystreet.map.domain.entity.GeneralLayerType
import ru.mystreet.map.domain.entity.GeneralSelectedMode

interface LayersConfigRepository {

    val layersConfig: Flow<List<GeneralLayer>>

    val selectedMode: Flow<GeneralSelectedMode>

    suspend fun setSelectedMode(mode: GeneralSelectedMode)

    suspend fun setLayerEnabled(layer: GeneralLayerType, enabled: Boolean)

}