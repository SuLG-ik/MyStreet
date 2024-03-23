package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.EditMapObjectField
import ru.mystreet.map.domain.repository.MapObjectsRepository

class EditMapObjectUseCase(
    private val remoteEditMapObjectUseCase: RemoteEditMapObjectUseCase,
    private val addTagToFieldUseCase: AddTagToFieldUseCase,
) {

    suspend operator fun invoke(field: EditMapObjectField) {
        val newTags = addTagToFieldUseCase(field.tags)
        remoteEditMapObjectUseCase(field.copy(tags = newTags))
    }

}