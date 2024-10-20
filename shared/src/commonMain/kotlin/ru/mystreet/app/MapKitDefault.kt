package ru.mystreet.app

import ru.sulgik.mapkit.MapKit


fun initializeMapKit() {
    MapKit.setApiKey(BuildKonfig.API_KEY)
    MapKit.setLocale("ru_RU")
}