@file:OptIn(ExperimentalForeignApi::class)

package ru.mystreet.app

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import cocoapods.YandexMapsMobile.YMKLogoPadding.Companion.paddingWithHorizontalPadding
import cocoapods.YandexMapsMobile.YMKMapView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.darwin.NSInteger
import platform.darwin.NSUInteger
import ru.mystreet.map.Map

@Composable
actual fun MapView(
    mapController: MapController,
    modifier: Modifier
) {
    val mapView = remember { YMKMapView() }
    DisposableEffect(mapView, mapController) {
        val map = mapView.mapWindow?.map
        if (map != null)
            mapController.bindAnchor(Map(map))
        onDispose {
            mapController.unbindAnchor()
        }
    }
    UIKitView(factory = { mapView }, modifier = modifier)
}