package ru.mystreet.map.map.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import ru.mystreet.map.component.Map

@Composable
fun MapUI(
    map: Map,
    modifier: Modifier,
) {
    MapScreen(
        mapController = map.mapController,
        isNecessaryZoomAlert = map.isNecessaryZoomAlert.collectAsState(false).value,
        onZoomIn = map::onZoomInPress,
        onZoomOut = map::onZoomOutPress,
        onFollowLocation = map::onFollowLocation,
        onLocationPermissionGranted = map::onLocationPermissionGranted,
        modifier = modifier,
    )
}