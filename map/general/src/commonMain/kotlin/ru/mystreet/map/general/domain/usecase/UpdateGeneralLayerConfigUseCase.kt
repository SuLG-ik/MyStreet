package ru.mystreet.map.general.domain.usecase

import ru.mystreet.map.domain.entity.GeneralLayerType
import ru.mystreet.map.general.domain.repository.LayersConfigRepository

class UpdateGeneralLayerConfigUseCase(
    private val layersConfigRepository: LayersConfigRepository,
) {

    suspend operator fun invoke(layer: GeneralLayerType, isEnabled: Boolean) {
        return layersConfigRepository.setLayerEnabled(layer, isEnabled)
    }

}