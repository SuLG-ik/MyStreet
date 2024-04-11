package ru.mystreet.app

import android.content.Context
import ru.mystreet.app.di.platformStartDI
import ru.sulgik.core.crashlytics.setupCrashlytics

fun Context.startApp() {
    setupCrashlytics()
    initializeMapKit()
    platformStartDI(this)
}