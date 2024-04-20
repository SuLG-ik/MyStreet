package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.EditMapObjectField

class TrimEditMapObjectFieldUseCase {

    operator fun invoke(field: EditMapObjectField): EditMapObjectField {
        return field.copy(
            title = field.title.copy(value = field.title.value.trim()),
            description = field.description.copy(value = field.description.value.trim()),
        )
    }

}