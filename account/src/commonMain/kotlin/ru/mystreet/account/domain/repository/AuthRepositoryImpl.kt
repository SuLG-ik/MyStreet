package ru.mystreet.account.domain.repository

import ru.mystreet.account.domain.entity.LoginResponse
import ru.mystreet.account.domain.entity.RegisterResponse

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(login: String, password: String): LoginResponse {
        TODO("Not yet implemented")
    }

    override suspend fun register(
        username: String,
        password: String,
        email: String,
        name: String,
    ): RegisterResponse {
        TODO("Not yet implemented")
    }
}