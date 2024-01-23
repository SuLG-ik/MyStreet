package ru.mystreet.app.component.map

import ru.mystreet.app.AssetsStore
import ru.mystreet.app.MapController

fun MapController.locationProvider(
    assetsStore: AssetsStore,
): UserLocationProvider {
    return MapUserLocationProvider(this, assetsStore)
}

private class MapUserLocationProvider(
    private val mapController: MapController,
    private val assetsStore: AssetsStore
) : UserLocationProvider {
    override fun start() {
        mapController.setUserLocation(assetsStore.userLocationAsset)
    }

    override fun stop() {
    }

}
