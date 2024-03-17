package ru.mystreet.account.domain.usecase

import ru.mystreet.account.domain.entity.LoginField
import ru.mystreet.account.domain.service.AuthFieldValidator

class ValidateLoginUseCase(
    private val validator: AuthFieldValidator,
) {

    operator fun invoke(value: String): LoginField.FieldError? {
        return validator.validateLogin(value)
    }

}