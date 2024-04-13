package ru.mystreet.account.data.service

import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.service.AuthFieldFormatter

@Factory
class DirectAuthFieldFormatter : AuthFieldFormatter {
    override fun formatLogin(value: String): String {
        return value.filterNot(Char::isWhitespace)
    }

    override fun formatEmail(value: String): String {
        return value.filterNot(Char::isWhitespace)
    }

    override fun formatName(value: String): String {
        return value.trimStart()
    }

    override fun formatPassword(value: String): String {
        return value.filterNot(Char::isWhitespace)
    }

    override fun formatPasswordRepeat(value: String): String {
        return value.filterNot(Char::isWhitespace)
    }
}