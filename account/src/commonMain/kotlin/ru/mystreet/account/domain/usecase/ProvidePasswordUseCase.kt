package ru.mystreet.account.domain.usecase

import arrow.core.Ior
import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.FieldError
import ru.mystreet.uikit.ValidatedField

@Factory
class ProvidePasswordUseCase(
    private val format: FormatPasswordUseCase,
    private val validate: ValidatePasswordUseCase,
) {

    operator fun invoke(value: String): ValidatedField<FieldError> {
        val formattedPassword = format(value)
        return validate(formattedPassword)
    }

}