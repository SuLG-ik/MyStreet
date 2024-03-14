package ru.mystreet.core.auth

import kotlin.jvm.JvmInline

@JvmInline
value class AuthScope(
    val username: String,
)


data class PrivateAuthScope(
    val username: String,
    val password: String,
)