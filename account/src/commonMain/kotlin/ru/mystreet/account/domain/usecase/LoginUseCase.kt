package ru.mystreet.account.domain.usecase

import org.koin.core.annotation.Factory
import ru.mystreet.core.auth.AuthScope
import ru.mystreet.core.auth.domain.usecase.SaveLocalLogin

@Factory
class LoginUseCase(
    private val remoteLoginUseCase: RemoteLoginUseCase,
    private val saveLocalLogin: SaveLocalLogin,
) {

    suspend operator fun invoke(login: String, password: String): AuthScope {
        val remoteLoginResponse = remoteLoginUseCase(login, password)
        return saveLocalLogin(remoteLoginResponse.username, password)
    }

}