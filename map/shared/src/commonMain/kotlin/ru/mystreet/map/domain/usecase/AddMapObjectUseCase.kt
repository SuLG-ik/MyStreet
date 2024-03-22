package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.domain.entity.MapObjectPart
import ru.mystreet.map.domain.repository.MapObjectsRepository

class AddMapObjectUseCase(
    private val repository: MapObjectsRepository,
    private val addLocalFramedMapObjectUseCase: AddLocalFramedMapObjectUseCase,
) {

    suspend operator fun invoke(field: AddMapObjectField) {
        val mapObject = repository.addMapObject(
            title = field.title.value,
            description = field.description.value,
            category = field.category,
            latitude = field.point.latitude,
            longitude = field.point.longitude,
            tags = field.tags.tags.tags,
        )
        addLocalFramedMapObjectUseCase(
            MapObjectPart(
                id = mapObject.id,
                title = mapObject.title,
                point = mapObject.point,
                category = mapObject.category
            )
        )
    }

}