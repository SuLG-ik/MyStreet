package ru.mystreet.errors.domain.exception

import ru.mystreet.errors.domain.ErrorInfo

class PermissionDeniedException() : Exception(), HandleableError {

    override val data: ErrorInfo.ErrorData = ErrorInfo.ErrorData.Raw(
        title = "Недосаточно прав",
        content = "Попробуйте войти в другой аккаунт, при наличии"
    )

    override val config: ErrorInfo.Config = ErrorInfo.Config(key = "PermissionDeniedException")

}