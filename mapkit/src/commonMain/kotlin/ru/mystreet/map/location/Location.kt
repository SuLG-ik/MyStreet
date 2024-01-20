package ru.mystreet.map.location

import ru.mystreet.map.geomety.Point

sealed interface LocationUpdateEvent {
    
    data class LocationUpdate(val location: Location): LocationUpdateEvent
    
    data class LocationStatusUpdate(val status: LocationStatus): LocationUpdateEvent
    
}

data class Location(
    val position: Point,
    val accuracy: Double?,
    val altitude: Double?,
    val altitudeAccuracy: Double?,
    val heading: Double?,
    val speed: Double?,
    val absoluteTimestamp: Long,
    val relativeTimestamp: Long,
)


enum class LocationStatus {
    NOT_AVAILABLE,
    AVAILABLE,
    RESET,
}
