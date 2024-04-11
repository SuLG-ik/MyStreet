package ru.sulgik.core.crashlytics

import co.touchlab.crashkios.crashlytics.enableCrashlytics
import co.touchlab.crashkios.crashlytics.setCrashlyticsUnhandledExceptionHook

internal actual fun setupNativeCrashlytics() {
    enableCrashlytics()
    setCrashlyticsUnhandledExceptionHook()
}