package ru.mystreet.account.domain.usecase

import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.RegisterField

@Factory
class RegisterIsContinueAvailableUseCase {

    operator fun invoke(field: RegisterField): Boolean {
        return field.login.leftOrNull() == null && field.password.leftOrNull() == null &&
                field.email.leftOrNull() == null && field.passwordRepeat.leftOrNull() == null &&
                field.name.leftOrNull() == null
    }

}