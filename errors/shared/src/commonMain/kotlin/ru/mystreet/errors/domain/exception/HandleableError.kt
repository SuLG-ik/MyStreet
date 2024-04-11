package ru.mystreet.errors.domain.exception

import ru.mystreet.errors.domain.ErrorInfo

interface HandleableError {

    val data: ErrorInfo.ErrorData

    val config: ErrorInfo.Config

}
