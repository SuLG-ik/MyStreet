package ru.mystreet.account.domain.usecase

import ru.mystreet.account.domain.entity.LoginField
import ru.mystreet.uikit.ValidatedField

class ProvidePasswordUseCase(
    private val formatPasswordUseCase: FormatPasswordUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
) {

    operator fun invoke(value: String): ValidatedField<LoginField.FieldError> {
        val formattedPassword = formatPasswordUseCase(value)
        return ValidatedField(formattedPassword, validatePasswordUseCase(formattedPassword))
    }

}