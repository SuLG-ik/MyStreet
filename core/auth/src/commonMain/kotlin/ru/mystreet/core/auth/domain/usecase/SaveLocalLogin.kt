package ru.mystreet.core.auth.domain.usecase

import ru.mystreet.core.auth.AuthScope

class SaveLocalLogin {

    suspend operator fun invoke(username: String, password: String): AuthScope {
        TODO()
    }

}