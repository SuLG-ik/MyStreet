package ru.mystreet.errors.domain.exception

import ru.mystreet.errors.domain.ErrorInfo

class UnknownCategoryException(
    categoryId: String,
) : Exception(), HandleableError {

    override val data: ErrorInfo.ErrorData = ErrorInfo.ErrorData.Raw(
        title = "Приложение устарело",
    )

    override val config: ErrorInfo.Config = ErrorInfo.Config(key = "UnknownCategoryException")

}