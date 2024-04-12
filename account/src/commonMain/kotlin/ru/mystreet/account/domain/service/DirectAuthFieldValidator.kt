package ru.mystreet.account.domain.service

import arrow.core.Ior
import arrow.core.raise.ensure
import arrow.core.raise.ior
import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.FieldError

val emailRegex =
    "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+".toRegex()

@Factory
class DirectAuthFieldValidator : AuthFieldValidator {

    override fun validateEmail(value: String): Ior<FieldError, String> =
        ior(combineError = { first, _ -> first }) {
            ensure(value.isNotBlank()) { FieldError.IllegalLength }
            ensure(emailRegex.matches(value)) { FieldError.IllegalInput }
            value
        }

    override fun validateLogin(value: String): Ior<FieldError, String> =
        ior(combineError = { first, _ -> first }) {
            ensure(value.isNotBlank()) { FieldError.IllegalLength }
            value
        }

    override fun validateName(value: String): Ior<FieldError, String> =
        ior(combineError = { first, _ -> first }) {
            ensure(value.isNotBlank()) { FieldError.IllegalLength }
            value
        }

    override fun validatePassword(value: String): Ior<FieldError, String> =
        ior(combineError = { first, _ -> first }) {
            ensure(value.isNotBlank()) { FieldError.IllegalLength }
            value
        }


    override fun validateRepeatPassword(
        value: String,
        originalPassword: String,
    ): Ior<FieldError, String> =
        ior(combineError = { first, _ -> first }) {
            ensure(value == originalPassword) { FieldError.IllegalInput }
            value
        }

}