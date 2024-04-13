package ru.mystreet.account.domain.service

import arrow.core.Ior
import ru.mystreet.account.domain.entity.FieldError
import ru.mystreet.uikit.ValidatedField

interface AuthFieldValidator {
    fun validateLogin(value: String): ValidatedField<FieldError>
    fun validatePassword(value: String): ValidatedField<FieldError>
    fun validateEmail(value: String): ValidatedField<FieldError>
    fun validateRepeatPassword(value: String, originalPassword: String): ValidatedField<FieldError>
    fun validateName(value: String): ValidatedField<FieldError>
}