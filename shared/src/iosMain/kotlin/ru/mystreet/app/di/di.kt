package ru.mystreet.app.di

import cocoapods.YandexMapsMobile.YMKMapKit
import cocoapods.YandexMapsMobile.mapKit
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.mystreet.map.image.ImageProviderFactory
import ru.mystreet.map.location.LocationManager

fun platformStartDI() {
    startKoin {
        modules(appModule)
    }
}

@OptIn(ExperimentalForeignApi::class)
actual val platformYandexMapModule: Module = module {
    single { LocationManager(YMKMapKit.mapKit().createLocationManager()) }
    singleOf(::ImageProviderFactory)
}