package ru.sulgik.core.graphql

import com.apollographql.apollo.api.http.HttpRequest

class AuthProviderAuthRequestConsumer(
    private val authProvider: AuthProvider,
) : AuthRequestConsumer {

    override suspend fun consume(request: HttpRequest): HttpRequest {
        val token = authProvider.getToken() ?: return request
        return consume(request, token)
    }

    private fun consume(request: HttpRequest, token: AuthToken): HttpRequest {
        return when (token) {

            is AuthToken.Header -> consume(request, token)
            is AuthToken.Mixed -> token.tokens.fold(request) { acc: HttpRequest, authToken: AuthToken ->
                consume(acc, authToken)
            }
        }
    }

    private fun consume(request: HttpRequest, token: AuthToken.Header): HttpRequest {
        return request.newBuilder().addHeader(token.headerName, token.value).build()
    }

}