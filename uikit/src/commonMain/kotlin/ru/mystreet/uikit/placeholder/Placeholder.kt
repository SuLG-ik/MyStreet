package ru.mystreet.uikit.placeholder

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.node.Ref
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun PlaceholderDefaults.color(
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    contentAlpha: Float = 0.1f,
): Color = contentColor.copy(contentAlpha).compositeOver(backgroundColor)

@Composable
fun PlaceholderDefaults.fadeHighlightColor(
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    alpha: Float = 0.3f,
): Color = backgroundColor.copy(alpha = alpha)


@Composable
fun PlaceholderDefaults.shimmerHighlightColor(
    backgroundColor: Color = MaterialTheme.colorScheme.inverseSurface,
    alpha: Float = 0.75f,
): Color {
    return backgroundColor.copy(alpha = alpha)
}

fun Modifier.placeholder(
    color: Color = Color.Unspecified,
    shape: Shape? = null,
    highlight: PlaceholderHighlight? = null,
): Modifier = composed {
    Modifier.commonPlaceholder(
        color = if (color.isSpecified) color else PlaceholderDefaults.color(),
        shape = shape ?: MaterialTheme.shapes.large,
        highlight = highlight ?: PlaceholderHighlight.shimmer(),
    )
}

object PlaceholderDefaults {
    /**
     * The default [InfiniteRepeatableSpec] to use for [fade].
     */
    val fadeAnimationSpec: InfiniteRepeatableSpec<Float> by lazy {
        infiniteRepeatable(
            animation = tween(delayMillis = 200, durationMillis = 600),
            repeatMode = RepeatMode.Reverse,
        )
    }

    /**
     * The default [InfiniteRepeatableSpec] to use for [shimmer].
     */
    val shimmerAnimationSpec: InfiniteRepeatableSpec<Float> by lazy {
        infiniteRepeatable(
            animation = tween(durationMillis = 1700, delayMillis = 200),
            repeatMode = RepeatMode.Restart
        )
    }
}

fun Modifier.commonPlaceholder(
    color: Color,
    shape: Shape = RectangleShape,
    highlight: PlaceholderHighlight,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "placeholder"
        properties["color"] = color
        properties["highlight"] = highlight
        properties["shape"] = shape
    }
) {
    // Values used for caching purposes
    val lastSize = remember { Ref<Size>() }
    val lastLayoutDirection = remember { Ref<LayoutDirection>() }
    val lastOutline = remember { Ref<Outline>() }

    val infiniteTransition = rememberInfiniteTransition()
    val highlightProgress: Float = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = highlight.animationSpec,
    ).value


    remember { Paint() }
    remember(color, shape, highlight) {
        drawWithContent {
            lastOutline.value = drawPlaceholder(
                shape = shape,
                color = color,
                highlight = highlight,
                progress = highlightProgress,
                lastOutline = lastOutline.value,
                lastLayoutDirection = lastLayoutDirection.value,
                lastSize = lastSize.value,
            )
            // Keep track of the last size & layout direction
            lastSize.value = size
            lastLayoutDirection.value = layoutDirection
        }
    }
}

private fun DrawScope.drawPlaceholder(
    shape: Shape,
    color: Color,
    highlight: PlaceholderHighlight?,
    progress: Float,
    lastOutline: Outline?,
    lastLayoutDirection: LayoutDirection?,
    lastSize: Size?,
): Outline? {
    // shortcut to avoid Outline calculation and allocation
    if (shape === RectangleShape) {
        // Draw the initial background color
        drawRect(color = color)

        if (highlight != null) {
            drawRect(
                brush = highlight.brush(progress, size),
                alpha = highlight.alpha(progress),
            )
        }
        // We didn't create an outline so return null
        return null
    }

    // Otherwise we need to create an outline from the shape
    val outline = lastOutline.takeIf {
        size == lastSize && layoutDirection == lastLayoutDirection
    } ?: shape.createOutline(size, layoutDirection, this)

    // Draw the placeholder color
    drawOutline(outline = outline, color = color)

    if (highlight != null) {
        drawOutline(
            outline = outline,
            brush = highlight.brush(progress, size),
            alpha = highlight.alpha(progress),
        )
    }

    // Return the outline we used
    return outline
}