package ru.mystreet.app.di

import android.content.Context
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.map.location.LocationManager
import com.yandex.mapkit.location.LocationManager as YLocationManager

fun platformStartDI(context: Context) {
    startKoin {
        androidLogger()
        androidContext(context)
        modules(appModule)
    }
}

actual val platformYandexMapModule = module {
    single(createdAtStart = true) {
        MapKitFactory.initialize(get())
        MapKitFactory.getInstance()
    } bind MapKit::class
    single { get<MapKit>().createLocationManager() } bind YLocationManager::class
    singleOf(::LocationManager)
}