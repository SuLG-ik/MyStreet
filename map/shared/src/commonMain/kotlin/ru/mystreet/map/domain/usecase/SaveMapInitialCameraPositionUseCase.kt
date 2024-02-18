package ru.mystreet.map.domain.usecase

import ru.mystreet.map.CameraPosition
import ru.mystreet.map.domain.repository.LocalMapConfigRepository

class SaveMapInitialCameraPositionUseCase(
    private val localMapConfigRepository: LocalMapConfigRepository,
) {

    suspend operator fun invoke(point: CameraPosition) {
        localMapConfigRepository.updateInitialCameraPosition(point)
    }

}