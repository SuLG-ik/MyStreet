package ru.mystreet.account.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode
import org.koin.core.annotation.Factory
import ru.mystreet.account.data.converter.AuthConverter
import ru.mystreet.account.data.entity.RemoteLoginRequest
import ru.mystreet.account.data.entity.RemoteLoginResponse
import ru.mystreet.account.data.entity.RemoteRegisterRequest
import ru.mystreet.account.data.entity.RemoteRegisterResponse
import ru.mystreet.account.domain.entity.LoginResponse
import ru.mystreet.account.domain.entity.RegisterResponse
import ru.mystreet.account.domain.exception.UserIncorrectCredentials
import ru.mystreet.account.domain.repository.AuthRepository

@Factory
class KtorAuthRepository(
    private val converter: AuthConverter,
    private val client: HttpClient,
) : AuthRepository {
    override suspend fun login(login: String, password: String): LoginResponse {
        val response = client.post("/auth/login") {
            setBody(RemoteLoginRequest(username = login, password = password))
        }
        if (response.status == HttpStatusCode.Unauthorized)
            throw UserIncorrectCredentials()
        return with(converter) { response.body<RemoteLoginResponse>().convert() }
    }

    override suspend fun register(
        username: String,
        password: String,
        email: String,
        name: String,
    ): RegisterResponse {
        val response = client.post("/auth/register") {
            setBody(
                RemoteRegisterRequest(
                    username = username,
                    password = password,
                    email = email,
                    name = name,
                )
            )
        }.body<RemoteRegisterResponse>()
        return with(converter) { response.convert() }
    }
}