package ru.mystreet.account.domain.usecase

import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.LoginField

@Factory
class LoginIsContinueAvailableUseCase {

    operator fun invoke(field: LoginField): Boolean {
        return field.login.leftOrNull() == null && field.password.leftOrNull() == null
    }

}