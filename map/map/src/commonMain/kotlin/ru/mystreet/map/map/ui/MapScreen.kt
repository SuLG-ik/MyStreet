package ru.mystreet.map.map.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import ru.mystreet.app.MapController
import ru.sulgik.mapkit.compose.MapControllerEffect
import ru.sulgik.mapkit.compose.YandexMap
import ru.sulgik.mapkit.compose.bindToLifecycleOwner
import ru.sulgik.mapkit.compose.rememberAndInitializeMapKit
import ru.sulgik.mapkit.compose.rememberYandexMapController

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
    rememberAndInitializeMapKit().bindToLifecycleOwner(LocalLifecycleOwner.current)
    Box {
        val controller = rememberYandexMapController()
        MapControllerEffect(
            controller = controller,
            dispose = {
                mapController.unbindAnchor()
            }
        ) {
            mapController.bindAnchor(it)
        }
        YandexMap(
            controller = controller,
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