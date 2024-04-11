package ru.sulgik.core.crashlytics

import ru.mystreet.errors.domain.ErrorInfo
import ru.mystreet.errors.domain.ErrorLevel
import ru.mystreet.errors.domain.ErrorPostProcessor

class ErrorInfoException(
    errorInfo: ErrorInfo,
) : Exception(errorInfo.toString())

class CrashlyticsErrorPostProcessor : ErrorPostProcessor {
    override fun process(error: ErrorInfo) {
        if (error.config.level < ErrorLevel.ERROR)
            return
        val parent = error.parent
        if (parent is Throwable)
            Crashlytics.sendHandledException(parent)
        else
            Crashlytics.sendHandledException(ErrorInfoException(error))
    }
}