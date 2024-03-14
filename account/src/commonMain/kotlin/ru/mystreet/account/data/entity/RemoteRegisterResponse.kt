package ru.mystreet.account.data.entity

import kotlinx.serialization.Serializable

@Serializable
class RemoteRegisterResponse(
    val username: String,
)

@Serializable
class RemoteRegisterRequest(
    val username: String,
    val password: String,
    val email: String,
    val name: String,
)