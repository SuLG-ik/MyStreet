package ru.mystreet.map.map.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.mystreet.uikit.DefaultMapAlpha
import ru.mystreet.uikit.UIKitCombinedFilledTonalIconButton
import ru.mystreet.uikit.UIKitFilledTonalIconButton
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.Follow
import ru.mystreet.uikit.iconpack.uikiticonpack.ZoomIn
import ru.mystreet.uikit.iconpack.uikiticonpack.ZoomOut

@Composable
fun MapOverlay(
    isNecessaryZoomAlert: Boolean,
    onFollowLocation: () -> Unit,
    onLocationPermissionGranted: () -> Unit,
    onZoomInPress: (Boolean) -> Unit,
    onZoomOutPress: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        ZoomMapAlert(
            isNecessaryZoomAlert = isNecessaryZoomAlert,
            modifier = Modifier.padding(top = 30.dp).align(Alignment.TopCenter)
        )
        MapControls(
            onFollowLocation = onFollowLocation,
            onLocationPermissionGranted = onLocationPermissionGranted,
            onZoomInPress = onZoomInPress,
            onZoomOutPress = onZoomOutPress,
            modifier = Modifier.align(Alignment.CenterEnd).graphicsLayer(alpha = DefaultMapAlpha),
        )
    }
}

@Composable
fun ZoomMapAlert(
    isNecessaryZoomAlert: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter,
    ) {
        AnimatedVisibility(
            isNecessaryZoomAlert,
            enter = slideInVertically() + fadeIn(),
            exit = slideOutVertically() + fadeOut()
        ) {
            Text(
                text = "Приблизьте, чтобы отобразить объекты",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 16.sp,
                    color = Color.White,
                    shadow = Shadow(blurRadius = 12f)
                ),
                modifier = modifier,
            )
        }
    }
}

@Composable
fun MapControls(
    onFollowLocation: () -> Unit,
    onLocationPermissionGranted: () -> Unit,
    onZoomInPress: (Boolean) -> Unit,
    onZoomOutPress: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val controller: PermissionsController =
        remember(factory) { factory.createPermissionsController() }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    BindEffect(controller)
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier,
    ) {
        UIKitFilledTonalIconButton(
            onClick = {
                coroutineScope.launch {
                    try {
                        controller.providePermission(Permission.LOCATION)
                        controller.providePermission(Permission.COARSE_LOCATION)
                        onLocationPermissionGranted()
                        onFollowLocation()
                    } catch (exception: DeniedException) {
                        controller.openAppSettings()
                    } catch (exception: RequestCanceledException) {
                        controller.openAppSettings()
                    }
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