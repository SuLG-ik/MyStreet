package ru.mystreet.map.map.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.map.map.component.Map

@Composable
fun MapUI(
    map: Map,
    modifier: Modifier,
) {
    MapScreen(
        mapController = map.mapController,
        onZoomIn = map::onZoomInPress,
        onZoomOut = map::onZoomOutPress,
        onFollowLocation = map::onFollowLocation,
        modifier = modifier,
    )
}