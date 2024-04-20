package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.domain.entity.MapObjectPart

class AddMapObjectUseCase(
    private val addRemoteMapObjectUseCase: AddRemoteMapObjectUseCase,
    private val addLocalFramedMapObjectUseCase: AddLocalFramedMapObjectUseCase,
    private val trimAddMapObjectFieldUseCase: TrimAddMapObjectFieldUseCase,
) {

    suspend operator fun invoke(field: AddMapObjectField) {
        val trimmedField = trimAddMapObjectFieldUseCase(field)
        val mapObject = addRemoteMapObjectUseCase(trimmedField)
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