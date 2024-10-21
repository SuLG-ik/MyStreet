package ru.mystreet.app.android

import android.app.Application
import ru.mystreet.app.startApp

class MyStreetApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startApp()
    }
}