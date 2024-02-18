package ru.mystreet.map.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.CameraPosition
import ru.mystreet.map.domain.entity.MapConfig

interface LocalMapConfigRepository {

    val mapConfig: Flow<MapConfig>

    suspend fun updateInitialCameraPosition(cameraPosition: CameraPosition)

}