package ru.mystreet.core.auth

import kotlinx.coroutines.flow.Flow

interface AuthService {

    fun currentScope(): Flow<AuthScope?>

    fun currentPrivateScope(): Flow<PrivateAuthScope?>

    suspend fun setUser(username: String, password: String): AuthScope

}