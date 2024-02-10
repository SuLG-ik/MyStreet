package ru.mystreet.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView
import ru.mystreet.map.MapWindow

@Composable
actual fun MapView(
    mapController: MapController,
    modifier: Modifier
) {
    val isNightModeEnabled = isSystemInDarkTheme()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val mapView = remember { MapView(context) }
    val mapKit = remember {
        MapKitFactory.initialize(context)
        MapKitFactory.getInstance()
    }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    mapKit.onStart()
                    mapView.onStart()
                }

                Lifecycle.Event.ON_STOP -> {
                    mapView.onStop()
                    mapKit.onStop()
                }

                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        mapController.bindAnchor(
            MapWindow(
                mapView.mapWindow,
                context,
            )
        )
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            mapController.unbindAnchor()
        }
    }
    SideEffect {
        mapView.mapWindow.map.isNightModeEnabled = isNightModeEnabled
    }
    AndroidView(factory = { mapView }, modifier = modifier)
}