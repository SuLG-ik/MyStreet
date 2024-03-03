package ru.mystreet.map.map.domain.usecase

import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.entity.MapFrame
import ru.mystreet.map.map.domain.repository.RemoteMapObjectsRepository

class GetRemoteFramedMapObjectsUseCase(
    private val remoteMapObjectsRepository: RemoteMapObjectsRepository,
) {

    suspend operator fun invoke(frame: MapFrame): FramedMapObjects {
        return remoteMapObjectsRepository.getFramedMapObjects(frame)
    }

}