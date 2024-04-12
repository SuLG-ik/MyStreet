package ru.mystreet.account.domain.entity

import arrow.core.Ior

data class LoginField(
    val login: Ior<FieldError, String>,
    val password: Ior<FieldError, String>,
)

sealed interface FieldError {
    data object IllegalLength : FieldError
    data object IllegalInput : FieldError
}