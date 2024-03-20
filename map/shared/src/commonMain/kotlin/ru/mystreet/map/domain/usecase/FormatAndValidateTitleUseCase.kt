package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.FieldError
import ru.mystreet.uikit.ValidatedField

class FormatAndValidateTitleUseCase(
    private val formatTitleUseCase: FormatTitleUseCase,
    private val validateTitleUseCase: ValidateTitleUseCase,
) {

    operator fun invoke(value: String): ValidatedField<FieldError> {
        val formattedValue = formatTitleUseCase(value)
        val error =  validateTitleUseCase(formattedValue)
        return ValidatedField(formattedValue, error)
    }

}