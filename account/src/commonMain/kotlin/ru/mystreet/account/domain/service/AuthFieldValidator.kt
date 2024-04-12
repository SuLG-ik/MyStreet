package ru.mystreet.account.domain.service

import arrow.core.Ior
import ru.mystreet.account.domain.entity.FieldError

interface AuthFieldValidator {
    fun validateLogin(value: String): Ior<FieldError, String>
    fun validatePassword(value: String): Ior<FieldError, String>
    fun validateEmail(value: String): Ior<FieldError, String>
    fun validateRepeatPassword(value: String, originalPassword: String): Ior<FieldError, String>
    fun validateName(value: String): Ior<FieldError, String>
}