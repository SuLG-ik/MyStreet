package ru.sulgik.core.crashlytics

import co.touchlab.crashkios.crashlytics.CrashlyticsCalls

class KotlinCrashlyticsImpl(
    private val implementation: CrashlyticsCalls,
) : Crashlytics {
    override fun logMessage(message: String) {
        implementation.logMessage(message)
    }

    override fun sendHandledException(throwable: Throwable) {
        implementation.sendHandledException(throwable)
    }

    override fun sendFatalException(throwable: Throwable) {
        implementation.sendFatalException(throwable)
    }

    override fun setCustomValue(key: String, value: Any) {
        implementation.setCustomValue(key, value)
    }

    override fun setUserId(identifier: String) {
        implementation.setUserId(identifier)
    }
}