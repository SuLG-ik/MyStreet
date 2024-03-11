package ru.mystreet.account.domain.entity

data class RegisterField(
    val name: String = "",
    val login: String = "",
    val email: String = "",
    val password: String = "",
    val passwordRepeat: String = "",
)