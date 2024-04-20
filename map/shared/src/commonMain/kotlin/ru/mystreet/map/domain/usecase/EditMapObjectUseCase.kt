package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.EditMapObjectField

class EditMapObjectUseCase(
    private val remoteEditMapObjectUseCase: RemoteEditMapObjectUseCase,
    private val addTagToFieldUseCase: AddTagToFieldUseCase,
    private val trimEditMapObjectFieldUseCase: TrimEditMapObjectFieldUseCase,
) {

    suspend operator fun invoke(field: EditMapObjectField) {
        val newTags = addTagToFieldUseCase(field.tags)
        remoteEditMapObjectUseCase(trimEditMapObjectFieldUseCase(field.copy(tags = newTags)))
    }

}