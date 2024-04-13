package ru.mystreet.account.data.converter

import org.koin.core.annotation.Factory
import ru.mystreet.account.data.entity.RemoteLoginResponse
import ru.mystreet.account.data.entity.RemoteRegisterResponse
import ru.mystreet.account.domain.entity.LoginResponse
import ru.mystreet.account.domain.entity.RegisterResponse

@Factory
class AuthConverter {

    fun RemoteLoginResponse.convert(): LoginResponse {
        return LoginResponse(username)
    }
    fun RemoteRegisterResponse.convert(): RegisterResponse {
        return RegisterResponse(username)
    }

}