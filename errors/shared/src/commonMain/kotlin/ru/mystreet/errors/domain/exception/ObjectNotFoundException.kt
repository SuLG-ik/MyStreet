package ru.mystreet.errors.domain.exception

import ru.mystreet.errors.domain.ErrorInfo

class ObjectNotFoundException(
    name: String,
) : Exception(), HandleableError {

    override val data: ErrorInfo.ErrorData = ErrorInfo.ErrorData.Raw(
        title = "$name не найден, возможно, он удалён",
    )

    override val config: ErrorInfo.Config = ErrorInfo.Config(key = "ObjectNotFoundException")

}