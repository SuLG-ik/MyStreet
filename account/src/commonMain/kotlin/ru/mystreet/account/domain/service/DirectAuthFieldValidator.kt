package ru.mystreet.account.domain.service

import ru.mystreet.account.domain.entity.LoginField

class DirectAuthFieldValidator : AuthFieldValidator {

    override fun validateLogin(value: String): LoginField.FieldError? {
        if (value.isBlank()) {
            return LoginField.FieldError.IllegalLength
        }
        return null
    }

    override fun validatePassword(value: String): LoginField.FieldError? {
        if (value.isBlank()) {
            return LoginField.FieldError.IllegalLength
        }
        return null
    }

}