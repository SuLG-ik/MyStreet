package ru.mystreet.errors.domain.exception

import ru.mystreet.errors.domain.ErrorInfo
import ru.mystreet.errors.domain.ErrorLevel

class InternalException(message: String? = null, cause: Throwable? = null) :
    Exception(message, cause), HandleableError {

    override val data: ErrorInfo.ErrorData = ErrorInfo.ErrorData.Raw(
        title = "Внутреняя ошибка, уже чиним",
    )

    override val config: ErrorInfo.Config =
        ErrorInfo.Config(key = "InternalException", level = ErrorLevel.ERROR)

}

class UnknownException(message: String? = null, cause: Throwable? = null) :
    Exception(message, cause), HandleableError {

    override val data: ErrorInfo.ErrorData = ErrorInfo.ErrorData.Raw(
        title = "Неизвестная ошибка, уже чиним",
    )

    override val config: ErrorInfo.Config =
        ErrorInfo.Config(key = "UnknownException", level = ErrorLevel.ERROR)

}