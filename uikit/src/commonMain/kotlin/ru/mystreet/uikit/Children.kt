package ru.mystreet.uikit

import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.Child
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

val DefaultChildAnimation =
    fade(spring()) + scale(spring(), frontFactor = 0.8f, backFactor = 1.2f)

val LocalChildrenStackAnimator = staticCompositionLocalOf { DefaultChildAnimation }

@Composable
fun <C : Any, T : Any> UIKitChildren(
    stack: ChildStack<C, T>,
    modifier: Modifier = Modifier,
    animation: StackAnimation<C, T> = stackAnimation(LocalChildrenStackAnimator.current),
    content: @Composable (child: Child.Created<C, T>) -> Unit,
) {
    Children(
        stack = stack,
        modifier = modifier,
        animation = animation,
        content = content,
    )
}

@Composable
fun <C : Any, T : Any> UIKitChildren(
    stack: Value<ChildStack<C, T>>,
    modifier: Modifier = Modifier,
    animation: StackAnimation<C, T> = stackAnimation(LocalChildrenStackAnimator.current),
    content: @Composable (child: Child.Created<C, T>) -> Unit,
) {
    val state = stack.subscribeAsState()

    UIKitChildren(
        stack = state.value,
        modifier = modifier,
        animation = animation,
        content = content,
    )
}