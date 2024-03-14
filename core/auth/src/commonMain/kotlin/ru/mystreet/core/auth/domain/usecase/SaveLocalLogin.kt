package ru.mystreet.core.auth.domain.usecase

import ru.mystreet.core.auth.AuthScope
import ru.mystreet.core.auth.AuthService

class SaveLocalLogin(
    private val authService: AuthService,
) {

    suspend operator fun invoke(username: String, password: String): AuthScope {
        return authService.setUser(username, password)
    }

}