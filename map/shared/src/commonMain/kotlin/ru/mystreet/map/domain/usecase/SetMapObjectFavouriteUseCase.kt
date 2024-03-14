package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.repository.MapObjectsRepository

class SetMapObjectFavouriteUseCase(
    private val mapObjectsRepository: MapObjectsRepository,
) {

    suspend operator fun invoke(mapObjectId: Long, isFavourite: Boolean) {
        mapObjectsRepository.setMapObjectFavourite(mapObjectId, isFavourite)
    }

}