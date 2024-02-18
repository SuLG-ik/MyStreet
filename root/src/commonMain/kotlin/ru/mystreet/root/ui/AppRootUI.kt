package ru.mystreet.root.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.mystreet.map.root.ui.MapHostUI
import ru.mystreet.root.component.AppRoot

@Composable
fun AppRootUI(
    component: AppRoot,
    modifier: Modifier = Modifier,
) {
    AppRootScreen(
        modifier = modifier,
    ) {
        Children(component.childStack) {
            AppRootNavHost(it.instance, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun AppRootNavHost(
    child: AppRoot.Child,
    modifier: Modifier,
) {
    when (child) {
        AppRoot.Child.Initializing -> {}
        is AppRoot.Child.MapHost -> MapHostUI(child.component, modifier)
    }
}