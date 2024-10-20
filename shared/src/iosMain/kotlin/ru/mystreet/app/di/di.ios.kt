package ru.mystreet.app.di

import org.koin.core.context.startKoin

fun platformStartDI() {
    startKoin {
        modules(appModule)
    }
}