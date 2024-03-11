package ru.mystreet.core.auth

import kotlin.jvm.JvmInline

@JvmInline
value class AuthScope(
    val username: String,
)