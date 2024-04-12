package ru.mystreet.account.domain.entity

import arrow.core.Ior

data class RegisterField(
    val name: Ior<FieldError, String>,
    val login: Ior<FieldError, String>,
    val email: Ior<FieldError, String>,
    val password: Ior<FieldError, String>,
    val passwordRepeat: Ior<FieldError, String>,
)