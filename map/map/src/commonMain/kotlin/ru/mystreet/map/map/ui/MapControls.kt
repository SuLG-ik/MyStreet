package ru.mystreet.map.map.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.rememberMultiplePermissionsState
import com.mohamedrejeb.calf.permissions.rememberPermissionState
import ru.mystreet.uikit.DefaultMapAlpha
import ru.mystreet.uikit.UIKitCombinedFilledTonalIconButton
import ru.mystreet.uikit.UIKitFilledTonalIconButton
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.Follow
import ru.mystreet.uikit.iconpack.uikiticonpack.ZoomIn
import ru.mystreet.uikit.iconpack.uikiticonpack.ZoomOut

@Composable
fun MapOverlay(
    onFollowLocation: () -> Unit,
    onLocationPermissionGranted: () -> Unit,
    onZoomInPress: (Boolean) -> Unit,
    onZoomOutPress: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        MapControls(
            onFollowLocation = onFollowLocation,
            onLocationPermissionGranted = onLocationPermissionGranted,
            onZoomInPress = onZoomInPress,
            onZoomOutPress = onZoomOutPress,
            modifier = Modifier.align(Alignment.CenterEnd).graphicsLayer(alpha = DefaultMapAlpha),
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapControls(
    onFollowLocation: () -> Unit,
    onLocationPermissionGranted: () -> Unit,
    onZoomInPress: (Boolean) -> Unit,
    onZoomOutPress: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val openSettings = rememberPermissionState(Permission.FineLocation) {
        if (it)
            onLocationPermissionGranted()
    }
    val permissionLauncher =
        rememberMultiplePermissionsState(
            listOf(
                Permission.FineLocation,
                Permission.CoarseLocation
            )
        ) {
            if (it.any { it.value })
                onLocationPermissionGranted()
        }
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier,
    ) {
        UIKitFilledTonalIconButton(
            onClick = {
                permissionLauncher.launchMultiplePermissionRequest()
                if (!permissionLauncher.allPermissionsGranted) {
                    if (permissionLauncher.shouldShowRationale)
                        openSettings.openAppSettings()
                } else {
                    onFollowLocation()
                }
            },
            color = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ) {
            Icon(UIKitIconPack.Follow, contentDescription = "follow me")
        }
        UIKitCombinedFilledTonalIconButton(
            onPress = { onZoomInPress(true) },
            onPressRelease = { onZoomInPress(false) },
            color = MaterialTheme.colorScheme.surface,
        ) {
            Icon(UIKitIconPack.ZoomIn, contentDescription = "zoom in")
        }
        UIKitCombinedFilledTonalIconButton(
            onPress = { onZoomOutPress(true) },
            onPressRelease = { onZoomOutPress(false) },
            color = MaterialTheme.colorScheme.surface,
        ) {
            Icon(UIKitIconPack.ZoomOut, contentDescription = "zoom out")
        }
    }
}