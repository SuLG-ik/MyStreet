package ru.mystreet.map.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.component.EditMapBottomBar
import ru.mystreet.uikit.paddingBottomInsets
import ru.mystreet.uikit.paddingHorizontalInsets

@Composable
fun EditMapBottomBarUI(
    component: EditMapBottomBar,
    modifier: Modifier,
) {
    AnimatedVisibility(
        component.isVisible.subscribeAsState().value,
        enter = fadeIn() + slideInVertically { it },
        exit = fadeOut() + slideOutVertically { it },
        modifier = modifier.paddingBottomInsets(),
    ) {
        Children(
            component.childStack,
            animation = stackAnimation(slide()),
        ) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxSize(),
            ) {
                EditMapBottomBarNavHost(
                    child = it.instance,
                    modifier = Modifier.fillMaxWidth().paddingHorizontalInsets()
                )
            }
        }
    }
}

@Composable
fun EditMapBottomBarNavHost(child: EditMapBottomBar.Child, modifier: Modifier) {
    when (child) {
        is EditMapBottomBar.Child.SelectCategory -> EditMapSelectCategoryUI(
            component = child.component,
            modifier = modifier
        )

        is EditMapBottomBar.Child.EditInfo -> EditMapNewObjectInfoUI(
            component = child.component,
            modifier = modifier
        )

        is EditMapBottomBar.Child.Loading -> EditMapNewObjectLoadingUI(
            component = child.component, modifier = modifier
        )
    }
}