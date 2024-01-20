package ru.mystreet.map.location

import kotlinx.coroutines.flow.Flow
import ru.mystreet.map.location.LocationUpdateEvent


expect class LocationManager {

    fun locationUpdateFlow(
        config: LocationListenerConfig = LocationListenerConfig(),
    ): Flow<LocationUpdateEvent>
    
}