package ru.mystreet.map.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.domain.entity.MapConfig
import ru.mystreet.map.domain.repository.LocalMapConfigRepository

class LoadLocalMapConfigUseCase(
    private val localMapConfigRepository: LocalMapConfigRepository,
) {

    operator fun invoke(): Flow<MapConfig> {
        return localMapConfigRepository.mapConfig
    }

}