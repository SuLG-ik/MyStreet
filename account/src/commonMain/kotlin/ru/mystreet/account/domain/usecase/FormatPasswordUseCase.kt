package ru.mystreet.account.domain.usecase

import ru.mystreet.account.domain.service.AuthFieldFormatter

class FormatPasswordUseCase(
    private val formatter: AuthFieldFormatter,
) {

    operator fun invoke(value: String): String {
        return formatter.formatLogin(value)
    }

}