package ru.mystreet.app

import android.content.Context
import ru.mystreet.app.di.platformStartDI

fun Context.startApp() {
    initializeMapKit()
    platformStartDI(this)
}