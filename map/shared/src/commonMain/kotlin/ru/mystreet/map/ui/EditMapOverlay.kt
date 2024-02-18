package ru.mystreet.map.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.CenterPin
import ru.mystreet.uikit.tokens.UIKitSizeTokens

@Composable
fun EditMapOverlay(
    isShown: Boolean,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val translation = with(density) { UIKitSizeTokens.DefaultIconSize.toPx() }
    AnimatedVisibility(isShown, enter = fadeIn(), exit = fadeOut()) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center,
        ) {
            Image(
                imageVector = UIKitIconPack.CenterPin,
                contentDescription = null,
                modifier = Modifier.size(UIKitSizeTokens.DefaultIconSize)
                    .graphicsLayer(translationY = -translation),
            )
        }
    }
}