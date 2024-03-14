package ru.mystreet.account.domain.repository

import ru.mystreet.account.domain.entity.LoginResponse
import ru.mystreet.account.domain.entity.RegisterResponse

interface AuthRepository {

    suspend fun login(login: String, password: String): LoginResponse

    suspend fun register(username: String, password: String, email: String, name: String): RegisterResponse

}