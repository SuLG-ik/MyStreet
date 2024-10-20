package ru.mystreet.app

import ru.mystreet.app.di.platformStartDI
import ru.sulgik.core.crashlytics.setupCrashlytics

fun startApp() {
    setupCrashlytics()
    initializeMapKit()
    platformStartDI()
}