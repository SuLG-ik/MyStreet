package ru.mystreet.account.domain.usecase

import arrow.core.Ior
import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.FieldError
import ru.mystreet.account.domain.service.AuthFieldValidator
import ru.mystreet.uikit.ValidatedField

@Factory
class ValidatePasswordRepeatUseCase(
    private val validator: AuthFieldValidator,
) {

    operator fun invoke(value: String, originalPassword: String): ValidatedField<FieldError> {
        return validator.validateRepeatPassword(value, originalPassword)
    }

}