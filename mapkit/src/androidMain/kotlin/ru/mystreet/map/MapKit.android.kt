package ru.mystreet.map

import com.yandex.mapkit.MapKitFactory

actual class MapKit {
    actual companion object {
        actual fun initializeMapKit(apiKey: String, locale: String) {
            MapKitFactory.setApiKey(apiKey)
            MapKitFactory.setLocale(locale)
        }
    }


}