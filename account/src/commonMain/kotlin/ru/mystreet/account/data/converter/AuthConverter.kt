package ru.mystreet.account.data.converter

import ru.mystreet.account.data.entity.RemoteLoginResponse
import ru.mystreet.account.data.entity.RemoteRegisterResponse
import ru.mystreet.account.domain.entity.LoginResponse
import ru.mystreet.account.domain.entity.RegisterResponse

class AuthConverter {

    fun RemoteLoginResponse.convert(): LoginResponse {
        return LoginResponse(username)
    }
    fun RemoteRegisterResponse.convert(): RegisterResponse {
        return RegisterResponse(username)
    }

}