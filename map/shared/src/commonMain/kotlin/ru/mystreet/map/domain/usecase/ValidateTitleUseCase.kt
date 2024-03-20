package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.FieldError
import ru.mystreet.map.domain.validator.MapObjectFieldValidator

class ValidateTitleUseCase(
    private val validator: MapObjectFieldValidator,
) {

    operator fun invoke(value: String): FieldError? {
        return validator.validateTitle(value)
    }

}