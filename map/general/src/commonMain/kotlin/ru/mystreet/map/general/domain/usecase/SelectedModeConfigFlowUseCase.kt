package ru.mystreet.map.general.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.domain.entity.GeneralSelectedMode
import ru.mystreet.map.domain.repository.LayersConfigRepository

class SelectedModeConfigFlowUseCase(
    private val repository: LayersConfigRepository,
) {

    operator fun invoke(): Flow<GeneralSelectedMode> {
        return repository.selectedMode
    }

}