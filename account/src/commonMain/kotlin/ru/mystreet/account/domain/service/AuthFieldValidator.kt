package ru.mystreet.account.domain.service

import ru.mystreet.account.domain.entity.LoginField

interface AuthFieldValidator {

    fun validateLogin(value: String): LoginField.FieldError?

    fun validatePassword(value: String): LoginField.FieldError?

}