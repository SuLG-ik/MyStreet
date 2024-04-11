package ru.sulgik.core.crashlytics

import co.touchlab.crashkios.crashlytics.CrashlyticsKotlin


fun setupCrashlytics() {
    setupNativeCrashlytics()
    Crashlytics.instance = KotlinCrashlyticsImpl(CrashlyticsKotlin.implementation)
}

internal expect fun setupNativeCrashlytics()