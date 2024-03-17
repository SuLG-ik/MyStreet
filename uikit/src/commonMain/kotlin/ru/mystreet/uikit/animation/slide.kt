package ru.mystreet.uikit.animation

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimator

/**
 * A simple sliding animation. Children enter from one side and exit to another side.
 */
fun topBarSlide(
    animationSpec: FiniteAnimationSpec<Float> = tween(),
): StackAnimator =
    stackAnimator(animationSpec = animationSpec) { factor, direction, content ->
        content(Modifier.offsetYFactor(factor))
    }

private fun Modifier.offsetYFactor(factor: Float): Modifier =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        layout(placeable.width, placeable.height) {
            placeable.placeRelative(
                x = 0,
                y = (placeable.height.toFloat() * mapFactor(factor)).toInt()
            )
        }
    }

private fun mapFactor(factor: Float): Float {
    return if (factor > 0)
        -factor
    else
        factor
}