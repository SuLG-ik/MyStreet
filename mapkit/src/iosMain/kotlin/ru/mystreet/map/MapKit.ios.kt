package ru.mystreet.map

import cocoapods.YandexMapsMobile.YMKMapKit
import cocoapods.YandexMapsMobile.setApiKey
import cocoapods.YandexMapsMobile.setLocale
import cocoapods.YandexMapsMobile.sharedInstance
import kotlinx.cinterop.ExperimentalForeignApi

actual class MapKit {
    
    actual companion object {
        @OptIn(ExperimentalForeignApi::class)
        actual fun initializeMapKit(apiKey: String, locale: String) {
            YMKMapKit.setApiKey(apiKey);
            YMKMapKit.setLocale(locale);
            YMKMapKit.sharedInstance();
        }
    }

}