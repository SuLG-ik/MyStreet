package ru.mystreet.account.domain.usecase

import ru.mystreet.account.domain.entity.LoginField

class LoginIsContinueAvailableUseCase {

    operator fun invoke(field: LoginField): Boolean {
        return field.login.error == null && field.password.error == null
    }

}