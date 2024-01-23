package ru.mystreet.app

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.location.LocationViewSourceFactory
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import ru.mystreet.map.Map

@Composable
actual fun MapView(
    mapController: MapController,
    modifier: Modifier
) {
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
        Log.d("aaaaa", "fweqr")
        mapController.bindAnchor(Map(context, mapView.mapWindow.map, MapKitFactory.getInstance().createUserLocationLayer(mapView.mapWindow)))
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            mapController.unbindAnchor()
        }
    }
    AndroidView(factory = { mapView }, modifier = modifier)
}