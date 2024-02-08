package ru.mystreet.root.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        MapHostUI(
            component.mapHost,
            modifier = Modifier.fillMaxSize(),
        )
    }
}