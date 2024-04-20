package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.AddMapObjectField

class TrimAddMapObjectFieldUseCase {

    operator fun invoke(field: AddMapObjectField): AddMapObjectField {
        return field.copy(
            title = field.title.copy(value = field.title.value.trim()),
            description = field.description.copy(value = field.description.value.trim()),
        )
    }

}