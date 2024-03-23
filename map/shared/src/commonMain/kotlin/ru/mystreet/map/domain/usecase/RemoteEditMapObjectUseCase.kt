package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.EditMapObjectField
import ru.mystreet.map.domain.repository.MapObjectsRepository

class RemoteEditMapObjectUseCase(
    private val repository: MapObjectsRepository,
) {

    suspend operator fun invoke(field: EditMapObjectField) {
        repository.editMapObject(
            id = field.id,
            title = field.title.value,
            description = field.description.value,
            category = field.category,
            tags = field.tags.tags.tags,
        )
    }

}