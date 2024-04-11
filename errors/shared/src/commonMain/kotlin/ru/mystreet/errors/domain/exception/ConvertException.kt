package ru.mystreet.errors.domain.exception

import ru.mystreet.errors.domain.ErrorInfo
import ru.mystreet.errors.domain.ErrorLevel

class ConvertException: Exception(), HandleableError {

    override val data: ErrorInfo.ErrorData = ErrorInfo.ErrorData.Raw(
        title = "Устраевшее приложение",
        content = "Попробуйте обновить приложение или повторить попытку",
    )

    override val config: ErrorInfo.Config =
        ErrorInfo.Config(key = "ConvertException", level = ErrorLevel.ERROR)

}