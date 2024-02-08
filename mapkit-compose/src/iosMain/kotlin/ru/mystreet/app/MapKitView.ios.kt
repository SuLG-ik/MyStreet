@file:OptIn(ExperimentalForeignApi::class)

package ru.mystreet.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import cocoapods.YandexMapsMobile.YMKMapKit
import cocoapods.YandexMapsMobile.YMKMapView
import cocoapods.YandexMapsMobile.mapKit
import kotlinx.cinterop.ExperimentalForeignApi
import ru.mystreet.map.Map

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun MapView(
    mapController: MapController,
    modifier: Modifier
) {
    val isDarkMode = isSystemInDarkTheme()
    val mapView = remember { YMKMapView() }
    DisposableEffect(mapView, mapController) {
        val map = mapView.mapWindow?.map
        if (map != null) {
            map.nightModeEnabled = isDarkMode
            mapController.bindAnchor(
                Map(
                    mapView = mapView,
                    map = map,
                    userLocationLayer = YMKMapKit.mapKit()
                        .createUserLocationLayerWithMapWindow(mapView.mapWindow!!)
                )
            )
        }
        onDispose {
            mapController.unbindAnchor()
        }
    }
    UIKitView(factory = { mapView }, modifier = modifier)
}