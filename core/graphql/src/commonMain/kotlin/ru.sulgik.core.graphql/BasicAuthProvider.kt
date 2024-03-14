package ru.sulgik.core.graphql

import kotlinx.coroutines.flow.first
import okio.ByteString
import okio.ByteString.Companion.encodeUtf8
import okio.internal.commonAsUtf8ToByteArray
import ru.mystreet.core.auth.AuthService
import kotlin.io.encoding.Base64

class BasicAuthProvider(
    private val authService: AuthService,
) : AuthProvider {

    override suspend fun getToken(): AuthToken? {
        val authScope = authService.currentPrivateScope().first() ?: return null
        return AuthToken.Header(
            "Authorization",
            basic(authScope.username, authScope.password),
        )
    }

    private fun basic(
        username: String,
        password: String,
    ): String {
        val usernameAndPassword = "$username:$password"
        val encoded = usernameAndPassword.encodeUtf8().base64()
        return "Basic $encoded"
    }

    override suspend fun refreshAndGetToken(): AuthToken? {
        throw IllegalStateException("BasicAuthProvider does not support token refreshing")
    }
}