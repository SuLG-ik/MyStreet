package ru.sulgik.core.graphql

sealed class AuthToken {
    data class Header(
        val headerName: String,
        val value: String,
    ) : AuthToken()
    data class Mixed(
        val tokens: List<AuthToken>
    ): AuthToken()
}

interface AuthProvider {
    suspend fun getToken(): AuthToken?

    suspend fun refreshAndGetToken(): AuthToken?

}