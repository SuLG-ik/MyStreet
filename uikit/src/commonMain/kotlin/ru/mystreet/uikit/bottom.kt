package ru.mystreet.uikit

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.whiteBottom(color: Color = MaterialTheme.colorScheme.surface): Modifier {
    return this then drawWithContent {
        drawContent()
        drawRect(
            brush = Brush.verticalGradient(
                0.8f to Color.Transparent,
                0.9f to color.copy(alpha = 0.2f),
                1f to color,
            ),
        )
    }
}

@Composable
fun Modifier.whiteBottom(
    scrollState: ScrollState,
    color: Color = MaterialTheme.colorScheme.surface,
): Modifier {
    val animatedColorAlpha by animateFloatAsState(if (scrollState.canScrollForward) 1f else 0f, tween(250))
    val animatedAlpha by animateFloatAsState(if (scrollState.canScrollForward) 0.2f else 0f, tween(250))
    return this then drawWithContent {
        drawContent()
        drawRect(
            brush = Brush.verticalGradient(
                0.8f to Color.Transparent,
                0.9f to color.copy(alpha = animatedAlpha),
                1f to color.copy(alpha = animatedColorAlpha),
            ),
        )
    }
}