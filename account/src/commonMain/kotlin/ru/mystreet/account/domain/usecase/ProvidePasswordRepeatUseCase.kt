package ru.mystreet.account.domain.usecase

import arrow.core.Ior
import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.FieldError

@Factory
class ProvidePasswordRepeatUseCase(
    private val format: FormatPasswordRepeatUseCase,
    private val validate: ValidatePasswordRepeatUseCase,
) {

    operator fun invoke(value: String, originalPassword: String): Ior<FieldError, String> {
        val formattedValue = format(value)
        return validate(formattedValue, originalPassword)
    }

}