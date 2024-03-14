package ru.mystreet.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.AppIcon

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        UIKitIconPack.AppIcon,
        contentDescription = null,
        modifier = modifier.defaultMinSize(150.dp),
    )
}