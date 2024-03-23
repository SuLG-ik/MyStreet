package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.FramedMapObjects
import ru.mystreet.map.domain.entity.MapFrame
import ru.mystreet.map.domain.repository.RemoteMapObjectsRepository

class GetRemoteFramedMapObjectsUseCase(
    private val remoteMapObjectsRepository: RemoteMapObjectsRepository,
) {

    suspend operator fun invoke(frame: MapFrame): FramedMapObjects {
        return remoteMapObjectsRepository.getFramedMapObjects(frame)
    }

}