package ru.mystreet.map.location

import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManager
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import ru.mystreet.map.toCommon
import com.yandex.mapkit.location.Location as YLocation

actual class LocationManager(
    private val locationManager: LocationManager,
) {

    actual fun locationUpdateFlow(
        config: LocationListenerConfig,
    ): Flow<LocationUpdateEvent> {
        return callbackFlow {
            val listener =
                ContinuationLocationListener(this)
            locationManager.subscribeForLocationUpdates(
                config.desiredAccuracy,
                config.minTime,
                config.minDistance,
                config.background,
                config.filteringMode.toNative(),
                listener,
            )
            awaitClose { locationManager.unsubscribe(listener) }
        }
    }

}


class ContinuationLocationListener(
    private val continuation: ProducerScope<LocationUpdateEvent>
) : LocationListener {
    override fun onLocationUpdated(p0: com.yandex.mapkit.location.Location) {
        continuation.trySend(LocationUpdateEvent.LocationUpdate(p0.toData()))
    }

    override fun onLocationStatusUpdated(p0: com.yandex.mapkit.location.LocationStatus) {
        continuation.trySend(LocationUpdateEvent.LocationStatusUpdate(p0.toData()))
    }
}

private fun YLocation.toData(): Location {
    return Location(
        position = position.toCommon(),
        accuracy = accuracy,
        altitude = altitude,
        altitudeAccuracy = altitudeAccuracy,
        heading = heading,
        speed = speed,
        absoluteTimestamp = absoluteTimestamp,
        relativeTimestamp = relativeTimestamp,
    )
}

private fun FilteringMode.toNative(): com.yandex.mapkit.location.FilteringMode {
    return when (this) {
        FilteringMode.OFF -> com.yandex.mapkit.location.FilteringMode.OFF
        FilteringMode.ON -> com.yandex.mapkit.location.FilteringMode.OFF
    }
}


private fun com.yandex.mapkit.location.LocationStatus.toData(): LocationStatus {
    return when (this) {
        com.yandex.mapkit.location.LocationStatus.NOT_AVAILABLE -> LocationStatus.NOT_AVAILABLE
        com.yandex.mapkit.location.LocationStatus.AVAILABLE -> LocationStatus.AVAILABLE
        com.yandex.mapkit.location.LocationStatus.RESET -> LocationStatus.RESET
    }
}

