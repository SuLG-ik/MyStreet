package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.CameraPositionConfig
import ru.mystreet.map.domain.entity.LatitudeConfig
import ru.mystreet.map.domain.entity.LongitudeConfig
import ru.mystreet.map.domain.entity.PointConfig
import ru.mystreet.map.domain.repository.LocalMapConfigRepository
import ru.sulgik.mapkit.map.CameraPosition

class SaveMapInitialCameraPositionUseCase(
    private val localMapConfigRepository: LocalMapConfigRepository,
) {

    suspend operator fun invoke(point: CameraPosition) {
        val cameraPosition = CameraPositionConfig(
            target = PointConfig(
                latitude = LatitudeConfig(point.target.latitude.value),
                longitude = LongitudeConfig(point.target.longitude.value),
            ),
            zoom = point.zoom, azimuth = point.azimuth, tilt = point.tilt,
        )
        localMapConfigRepository.updateInitialCameraPosition(cameraPosition)
    }

}