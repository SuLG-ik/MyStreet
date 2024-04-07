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
        content(Modifier.offsetYFactor(factor, direction = 1))
    }

private fun Modifier.offsetYFactor(factor: Float, direction: Int): Modifier =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        layout(placeable.width, placeable.height) {
            placeable.placeRelative(
                x = 0,
                y = (placeable.height.toFloat() * mapFactor(factor, direction)).toInt()
            )
        }
    }

private fun mapFactor(factor: Float, direction: Int): Float {
    return if (factor > 0)
        -factor * direction
    else
        factor * direction
}

fun bottomBarSlide(
    animationSpec: FiniteAnimationSpec<Float> = tween(),
): StackAnimator =
    stackAnimator(animationSpec = animationSpec) { factor, direction, content ->
        content(Modifier.offsetYFactor(factor, direction = -1))
    }