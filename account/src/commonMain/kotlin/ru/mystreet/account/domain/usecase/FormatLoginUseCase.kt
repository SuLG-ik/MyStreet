package ru.mystreet.account.domain.usecase

import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.service.AuthFieldFormatter

@Factory
class FormatLoginUseCase(
    private val formatter: AuthFieldFormatter,
) {

    operator fun invoke(value: String): String {
        return formatter.formatLogin(value)
    }

}