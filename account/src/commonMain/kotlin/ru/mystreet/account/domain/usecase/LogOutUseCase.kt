package ru.mystreet.account.domain.usecase

import org.koin.core.annotation.Factory
import ru.mystreet.core.auth.AuthService

@Factory
class LogOutUseCase(
    private val authService: AuthService,
) {

    suspend operator fun invoke() {
        authService.removeUser()
    }

}