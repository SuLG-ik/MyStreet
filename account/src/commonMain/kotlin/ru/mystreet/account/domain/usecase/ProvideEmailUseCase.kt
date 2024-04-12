package ru.mystreet.account.domain.usecase

import arrow.core.Ior
import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.FieldError

@Factory
class ProvideEmailUseCase(
    private val format: FormatEmailUseCase,
    private val validate: ValidateEmailUseCase,
) {

    operator fun invoke(value: String): Ior<FieldError, String> {
        val formattedValue = format(value)
        return validate(formattedValue)
    }

}