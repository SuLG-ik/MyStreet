package ru.mystreet.account.domain.usecase

import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.RegisterField

@Factory
class RegisterIsContinueAvailableUseCase {

    operator fun invoke(field: RegisterField): Boolean {
        return field.login.error == null && field.password.error == null &&
                field.email.error == null && field.passwordRepeat.error == null &&
                field.name.error == null
    }

}