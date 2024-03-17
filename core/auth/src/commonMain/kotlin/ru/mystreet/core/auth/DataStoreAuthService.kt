package ru.mystreet.core.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import ru.mystreet.core.datastore.DatastoreFactory
import ru.mystreet.core.datastore.create

@Serializable
private data class AuthData(
    val credentials: AuthCredentials? = null,
)

@Serializable
private data class AuthCredentials(
    val username: String,
    val password: String,
)

class DataStoreAuthService(
    factory: DatastoreFactory,
) : AuthService {

    private val datastore = factory.create("auth", AuthData(null))

    override suspend fun removeUser() {
        datastore.updateData { it.copy(credentials = null) }
    }

    override fun currentScope(): Flow<AuthScope?> {
        return datastore.data.map { data ->
            data.toScope()
        }
    }

    override fun currentPrivateScope(): Flow<PrivateAuthScope?> {
        return datastore.data.map { data ->
            data.toPrivateScope()
        }
    }

    override suspend fun setUser(username: String, password: String): AuthScope {
        datastore.updateData {
            it.copy(
                credentials = AuthCredentials(
                    username = username,
                    password = password
                )
            )
        }
        return AuthScope(username)
    }

    private fun AuthData.toScope(): AuthScope? {
        return credentials?.let { AuthScope(it.username) }
    }

    private fun AuthData.toPrivateScope(): PrivateAuthScope? {
        return credentials?.let { PrivateAuthScope(it.username, it.password) }
    }

}