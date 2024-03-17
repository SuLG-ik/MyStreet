package ru.mystreet.account.domain.usecase

import ru.mystreet.core.auth.AuthService

class LogOutUseCase(
    private val authService: AuthService,
) {

    suspend operator fun invoke() {
        authService.removeUser()
    }

}