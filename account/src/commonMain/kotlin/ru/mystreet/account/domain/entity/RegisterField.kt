package ru.mystreet.account.domain.entity

data class RegisterField(
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val passwordRepeat: String = "",
)