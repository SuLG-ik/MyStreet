package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.FieldError
import ru.mystreet.uikit.ValidatedField

class FormatAndValidateDescriptionUseCase(
    private val formatDescriptionUseCase: FormatDescriptionUseCase,
    private val validateDescriptionUseCase: ValidateDescriptionUseCase,
) {

    operator fun invoke(value: String): ValidatedField<FieldError> {
        val formattedValue = formatDescriptionUseCase(value)
        val error =  validateDescriptionUseCase(formattedValue)
        return ValidatedField(formattedValue, error)
    }

}