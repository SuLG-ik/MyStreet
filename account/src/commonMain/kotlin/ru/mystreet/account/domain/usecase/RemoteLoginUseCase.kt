package ru.mystreet.account.domain.usecase

import ru.mystreet.account.domain.entity.LoginResponse
import ru.mystreet.account.domain.repository.AuthRepository

class RemoteLoginUseCase(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(login: String, password: String): LoginResponse {
        return authRepository.login(login, password)
    }

}