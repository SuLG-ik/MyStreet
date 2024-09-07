@file:OptIn(ExperimentalForeignApi::class)

package ru.mystreet.map.location

import cocoapods.YandexMapsMobile.YMKLocation
import cocoapods.YandexMapsMobile.YMKLocationDelegateProtocol
import cocoapods.YandexMapsMobile.YMKLocationFilteringMode
import cocoapods.YandexMapsMobile.YMKLocationManager
import cocoapods.YandexMapsMobile.YMKLocationStatus
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.Foundation.timeIntervalSince1970
import platform.darwin.NSObject
import ru.mystreet.map.toData

actual class LocationManager(
    private val locationManager: YMKLocationManager,
) {

    actual fun locationUpdateFlow(
        config: LocationListenerConfig,
    ): Flow<LocationUpdateEvent> {
        return callbackFlow<LocationUpdateEvent> {
            val locationListener = ContinuationLocationListener(continuation = this)
            locationManager.subscribeForLocationUpdatesWithDesiredAccuracy(
                desiredAccuracy = config.desiredAccuracy,
                minTime = config.minTime,
                minDistance = config.minDistance,
                allowUseInBackground = config.background,
                filteringMode = config.filteringMode.toNative(),
                locationListener = locationListener,
            )
            awaitClose { locationManager.unsubscribeWithLocationListener(locationListener) }
        }
    }

}

private fun FilteringMode.toNative(): YMKLocationFilteringMode {
    return when (this) {
        FilteringMode.OFF -> YMKLocationFilteringMode.YMKLocationFilteringModeOff
        FilteringMode.ON -> YMKLocationFilteringMode.YMKLocationFilteringModeOn
    }
}


class ContinuationLocationListener(
    private val continuation: SendChannel<LocationUpdateEvent>,
) : NSObject(), YMKLocationDelegateProtocol {
    override fun onLocationStatusUpdatedWithStatus(status: YMKLocationStatus) {
        continuation.trySend(LocationUpdateEvent.LocationStatusUpdate(status.toData()))
    }

    override fun onLocationUpdatedWithLocation(location: YMKLocation) {
        continuation.trySend(LocationUpdateEvent.LocationUpdate(location.toData()))
    }

}

private fun YMKLocation.toData(): Location {
    return Location(
        position.toData(),
        accuracy = accuracy?.doubleValue,
        altitude = altitude?.doubleValue,
        altitudeAccuracy = altitudeAccuracy?.doubleValue,
        heading = heading?.doubleValue,
        speed = speed?.doubleValue,
        absoluteTimestamp = (absoluteTimestamp.timeIntervalSince1970 * 1000).toLong(),
        relativeTimestamp = (relativeTimestamp.timeIntervalSince1970 * 1000).toLong(),
    )
}

private fun YMKLocationStatus.toData(): LocationStatus {
    return when (this) {
        YMKLocationStatus.YMKLocationStatusNotAvailable -> LocationStatus.NOT_AVAILABLE
        YMKLocationStatus.YMKLocationStatusAvailable -> LocationStatus.AVAILABLE
        YMKLocationStatus.YMKLocationStatusReset -> LocationStatus.RESET
        else -> LocationStatus.NOT_AVAILABLE
    }
}
