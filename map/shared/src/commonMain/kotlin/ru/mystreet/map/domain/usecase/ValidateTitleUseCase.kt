package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.TitleField
import ru.mystreet.map.domain.validator.MapObjectFieldValidator

class ValidateTitleUseCase(
    private val validator: MapObjectFieldValidator,
) {

    operator fun invoke(value: String): TitleField {
        return validator.validateTitle(value)
    }

}