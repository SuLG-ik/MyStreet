package ru.mystreet.root.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.uikit.theme.UIKitTheme

@Composable
fun AppRootScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    UIKitTheme {
        Surface(
            modifier = modifier
                .consumeWindowInsets(WindowInsets.safeContent),
            content = content,
        )
    }
}