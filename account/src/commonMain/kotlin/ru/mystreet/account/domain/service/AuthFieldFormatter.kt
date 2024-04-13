package ru.mystreet.account.domain.service

interface AuthFieldFormatter {
    fun formatLogin(value: String): String
    fun formatEmail(value: String): String
    fun formatName(value: String): String
    fun formatPassword(value: String): String
    fun formatPasswordRepeat(value: String): String
}