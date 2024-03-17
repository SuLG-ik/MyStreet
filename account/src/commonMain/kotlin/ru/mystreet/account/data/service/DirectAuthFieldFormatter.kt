package ru.mystreet.account.data.service

import ru.mystreet.account.domain.service.AuthFieldFormatter

class DirectAuthFieldFormatter : AuthFieldFormatter {
    override fun formatLogin(value: String): String {
        return value.filterNot(Char::isWhitespace)
    }

    override fun formatPassword(value: String): String {
        return value.filterNot(Char::isWhitespace)
    }
}