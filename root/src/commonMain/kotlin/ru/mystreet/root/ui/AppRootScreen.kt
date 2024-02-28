package ru.mystreet.root.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import ru.mystreet.uikit.theme.UIKitTheme

@Composable
fun AppRootScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    UIKitTheme {
        Surface(
            modifier = modifier.consumeWindowInsets(WindowInsets.safeContent),
            content = content,
        )
    }
}