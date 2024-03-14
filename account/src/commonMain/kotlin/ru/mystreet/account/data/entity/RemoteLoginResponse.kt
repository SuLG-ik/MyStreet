package ru.mystreet.account.data.entity

import kotlinx.serialization.Serializable

@Serializable
class RemoteLoginResponse(
    val username: String
)

@Serializable
class RemoteLoginRequest(
    val username: String,
    val password: String,
)