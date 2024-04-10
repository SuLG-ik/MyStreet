package ru.mystreet.map.domain.exception

import ru.mystreet.errors.domain.ErrorInfo
import ru.mystreet.errors.domain.HandleableError

class MapLoadingException : Exception(), HandleableError {

    override val data: ErrorInfo.ErrorData = ErrorInfo.ErrorData.Raw(
        title = "Ошибка загрузки карты",
        content = "Проверьте соединение или повторите попытку позже",
        color = null,
    )

    override val config: ErrorInfo.Config =
        ErrorInfo.Config(
            key = "MapLoadingException",
            isShownMultiple = false,
        )

}