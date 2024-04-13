package ru.mystreet.account.domain.service

import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.FieldError
import ru.mystreet.uikit.ValidatedField

val emailRegex =
    "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+".toRegex()

val passwordRegex =
    "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$".toRegex()

val usernameRegex =
    "^[a-z0-9_-]{3,15}\$".toRegex()

@Factory
class DirectAuthFieldValidator : AuthFieldValidator {

    override fun validateEmail(value: String): ValidatedField<FieldError> {
        return when {
            value.isBlank() -> ValidatedField(value, FieldError.EmptyField)
            !emailRegex.matches(value) -> ValidatedField(value, FieldError.IllegalInput)
            else -> ValidatedField(value, null)
        }
    }

    override fun validateLogin(value: String): ValidatedField<FieldError> {
        return when {
            value.isBlank() -> ValidatedField(value, FieldError.EmptyField)
            value.length < 3 -> ValidatedField(value, FieldError.IllegalLength(3))
            !usernameRegex.matches(value) -> ValidatedField(value, FieldError.IllegalInput)
            else -> ValidatedField(value, null)
        }
    }

    override fun validateName(value: String): ValidatedField<FieldError> {
        return when {
            value.isBlank() -> ValidatedField(value, FieldError.EmptyField)
            value.length < 3 -> ValidatedField(value, FieldError.IllegalLength(3))
            else -> ValidatedField(value, null)
        }
    }

    override fun validatePassword(value: String): ValidatedField<FieldError> {
        return when {
            value.isBlank() -> ValidatedField(value, FieldError.EmptyField)
            value.length < 8 -> ValidatedField(value, FieldError.IllegalLength(8))
            !passwordRegex.matches(value) -> ValidatedField(value, FieldError.IllegalInput)
            else -> ValidatedField(value, null)
        }
    }

    override fun validateRepeatPassword(
        value: String,
        originalPassword: String,
    ): ValidatedField<FieldError> {
        return when {
            value != originalPassword -> ValidatedField(value, FieldError.IllegalInput)
            else -> ValidatedField(value, null)
        }
    }

}