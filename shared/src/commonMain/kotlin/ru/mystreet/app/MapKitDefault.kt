package ru.mystreet.app

import ru.mystreet.map.MapKit

fun initializeMapKit() {
    MapKit.initializeMapKit(BuildKonfig.API_KEY, "ru_RU")
}