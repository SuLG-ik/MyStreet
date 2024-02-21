package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.MapObjectCategory
import ru.mystreet.map.domain.entity.MapObjectTag
import ru.mystreet.map.domain.repository.MapObjectTagRepository

class LoadMapObjectTagsWithTitleUseCase(
    private val mapObjectTagRepository: MapObjectTagRepository,
) {

    suspend operator fun invoke(category: MapObjectCategory, query: String): List<MapObjectTag> {
        return mapObjectTagRepository.getMapObjectsTag(category, query)
    }

}