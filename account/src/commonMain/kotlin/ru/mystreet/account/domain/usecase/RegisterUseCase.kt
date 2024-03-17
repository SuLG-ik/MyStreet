package ru.mystreet.account.domain.usecase

import ru.mystreet.account.domain.entity.RegisterResponse
import ru.mystreet.account.domain.repository.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(
        username: String,
        password: String,
        email: String,
        name: String,
    ): RegisterResponse {
        return authRepository.register(username, password, email, name)
    }

}