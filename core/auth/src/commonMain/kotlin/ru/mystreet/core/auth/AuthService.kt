package ru.mystreet.core.auth

import kotlinx.coroutines.flow.Flow

interface AuthService {

    fun currentScope(): Flow<AuthScope>

}