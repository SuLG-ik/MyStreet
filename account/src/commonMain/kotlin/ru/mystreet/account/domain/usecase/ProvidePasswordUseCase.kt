package ru.mystreet.account.domain.usecase

import arrow.core.Ior
import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.FieldError

@Factory
class ProvidePasswordUseCase(
    private val format: FormatPasswordUseCase,
    private val validate: ValidatePasswordUseCase,
) {

    operator fun invoke(value: String): Ior<FieldError, String> {
        val formattedPassword = format(value)
        return validate(formattedPassword)
    }

}