package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.domain.repository.MapObjectsRepository

class AddMapObjectUseCase(
    private val repository: MapObjectsRepository,
) {

    suspend operator fun invoke(field: AddMapObjectField) {
        repository.addMapObject(
            title = field.title,
            description = field.description,
            category = field.category,
            latitude = field.point.latitude,
            longitude = field.point.longitude,
        )
    }

}