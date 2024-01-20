package ru.mystreet.app.di

import cocoapods.YandexMapsMobile.YMKLocationManager
import cocoapods.YandexMapsMobile.YMKMapKit
import cocoapods.YandexMapsMobile.mapKit
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.map.location.LocationManager

fun platformStartDI() {
    startKoin {
        modules(AppModule().module)
    }
}

actual class PlatformYandexMapModule : ModuleHost {
    @OptIn(ExperimentalForeignApi::class)
    override val module: Module = module {
        single { LocationManager(YMKMapKit.mapKit().createLocationManager()) } 
    }
}