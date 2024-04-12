package ru.mystreet.account.domain.usecase

import arrow.core.Ior
import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.FieldError
import ru.mystreet.account.domain.service.AuthFieldValidator

@Factory
class ValidatePasswordRepeatUseCase(
    private val validator: AuthFieldValidator,
) {

    operator fun invoke(value: String, originalPassword: String): Ior<FieldError, String> {
        return validator.validateRepeatPassword(value, originalPassword)
    }

}