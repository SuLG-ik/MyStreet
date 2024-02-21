package ru.mystreet.map.general.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.domain.entity.GeneralLayer
import ru.mystreet.map.general.domain.repository.LayersConfigRepository

class GeneralLayersConfigFlowUseCase(
    private val repository: LayersConfigRepository,
) {

    operator fun invoke(): Flow<List<GeneralLayer>> {
        return repository.layersConfig
    }

}