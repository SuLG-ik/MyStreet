package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.repository.MapObjectsRepository

class AddRemoteMapObjectUseCase(
    private val repository: MapObjectsRepository,
) {
    suspend operator fun invoke(field: AddMapObjectField): MapObject {
        return repository.addMapObject(
            title = field.title.value,
            description = field.description.value,
            category = field.category,
            latitude = field.point.latitude,
            longitude = field.point.longitude,
            tags = field.tags.tags.tags,
        )
    }
}