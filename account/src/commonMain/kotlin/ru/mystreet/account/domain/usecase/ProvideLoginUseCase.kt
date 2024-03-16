package ru.mystreet.account.domain.usecase

import ru.mystreet.account.domain.entity.LoginField
import ru.mystreet.uikit.ValidatedField

class ProvideLoginUseCase(
    private val formatLoginUseCase: FormatLoginUseCase,
    private val validateLoginUseCase: ValidateLoginUseCase,
) {

    operator fun invoke(value: String): ValidatedField<LoginField.FieldError> {
        val formattedValue = formatLoginUseCase(value)
        return ValidatedField(formattedValue, validateLoginUseCase(formattedValue))
    }

}