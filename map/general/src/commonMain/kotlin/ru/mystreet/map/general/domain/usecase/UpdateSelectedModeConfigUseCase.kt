package ru.mystreet.map.general.domain.usecase

import ru.mystreet.map.domain.entity.GeneralLayerType
import ru.mystreet.map.domain.entity.GeneralSelectedMode
import ru.mystreet.map.domain.repository.LayersConfigRepository

class UpdateSelectedModeConfigUseCase(
    private val layersConfigRepository: LayersConfigRepository,
) {

    suspend operator fun invoke(mode: GeneralSelectedMode) {
        layersConfigRepository.setSelectedMode(mode)
    }

}