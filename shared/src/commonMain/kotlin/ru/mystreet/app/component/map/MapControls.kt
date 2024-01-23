package ru.mystreet.app.component.map

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
import ru.mystreet.app.resources.IconPack
import ru.mystreet.app.resources.iconpack.Follow
import ru.mystreet.app.resources.iconpack.ZoomIn
import ru.mystreet.app.resources.iconpack.ZoomOut
import ru.mystreet.app.ui.CombinedFilledTonalIconButton
import ru.mystreet.app.ui.FilledTonalIconButton

@Composable
fun MapOverlay(
    onFollowLocation: () -> Unit,
    onZoomInPress: (Boolean) -> Unit,
    onZoomOutPress: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        MapControls(
            onFollowLocation = onFollowLocation,
            onZoomInPress = onZoomInPress,
            onZoomOutPress = onZoomOutPress,
            modifier = Modifier.align(Alignment.CenterEnd).graphicsLayer(alpha = 0.9f),
        )
    }
}

@Composable
fun MapControls(
    onFollowLocation: () -> Unit,
    onZoomInPress: (Boolean) -> Unit,
    onZoomOutPress: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier,
    ) {
        FilledTonalIconButton(
            onClick = onFollowLocation,
            color = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ) {
            Icon(IconPack.Follow, contentDescription = "follow me")
        }
        CombinedFilledTonalIconButton(
            onPress = { onZoomInPress(true) },
            onPressRelease = { onZoomInPress(false) },
            color = MaterialTheme.colorScheme.surface,
        ) {
            Icon(IconPack.ZoomIn, contentDescription = "zoom in")
        }
        CombinedFilledTonalIconButton(
            onPress = { onZoomOutPress(true) },
            onPressRelease = { onZoomOutPress(false) },
            color = MaterialTheme.colorScheme.surface,
        ) {
            Icon(IconPack.ZoomOut, contentDescription = "zoom out")
        }
    }
}