package ru.sulgik.core.crashlytics

import co.touchlab.crashkios.crashlytics.enableCrashlytics

internal actual fun setupNativeCrashlytics() {
    enableCrashlytics()
}