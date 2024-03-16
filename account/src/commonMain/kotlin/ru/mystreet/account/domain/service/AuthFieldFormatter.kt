package ru.mystreet.account.domain.service

interface AuthFieldFormatter {

    fun formatLogin(value: String): String

    fun formatPassword(value: String): String

}