package ru.mystreet.map.map.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.app.MapController
import ru.mystreet.app.MapView

@Composable
fun MapScreen(
    mapController: MapController,
    isNecessaryZoomAlert: Boolean,
    onZoomIn: (Boolean) -> Unit,
    onZoomOut: (Boolean) -> Unit,
    onFollowLocation: () -> Unit,
    onLocationPermissionGranted: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        MapView(
            mapController = mapController,
            modifier = Modifier.fillMaxSize(),
        )
        MapOverlay(
            isNecessaryZoomAlert = isNecessaryZoomAlert,
            onFollowLocation = onFollowLocation,
            onLocationPermissionGranted = onLocationPermissionGranted,
            onZoomInPress = onZoomIn,
            onZoomOutPress = onZoomOut,
            modifier = modifier,
        )
    }
}