package ru.mystreet.root.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.mystreet.map.root.ui.MapHostUI
import ru.mystreet.root.component.AppRoot
import ru.mystreet.uikit.UIKitOutlineTextField

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