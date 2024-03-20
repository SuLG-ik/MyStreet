package ru.mystreet.map

expect class MapKit {

    companion object {
        fun initializeMapKit(apiKey: String, locale: String)
        fun resetLocationManager()
    }
    
}