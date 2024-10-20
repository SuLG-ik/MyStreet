package ru.mystreet.app.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun platformStartDI(context: Context) {
    startKoin {
        androidLogger()
        androidContext(context)
        modules(appModule)
    }
}