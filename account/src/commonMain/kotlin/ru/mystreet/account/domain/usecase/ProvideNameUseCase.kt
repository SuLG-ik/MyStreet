package ru.mystreet.account.domain.usecase

import arrow.core.Ior
import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.FieldError
import ru.mystreet.uikit.ValidatedField

@Factory
class ProvideNameUseCase(
    private val format: FormatNameUseCase,
    private val validate: ValidateNameUseCase,
) {

    operator fun invoke(value: String): ValidatedField<FieldError> {
        val formattedValue = format(value)
        return validate(formattedValue)
    }

}