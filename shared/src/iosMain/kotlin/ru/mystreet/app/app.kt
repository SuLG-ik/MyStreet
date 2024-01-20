package ru.mystreet.app

import ru.mystreet.app.di.platformStartDI

fun startApp() {
    initializeMapKit()
    platformStartDI()
}