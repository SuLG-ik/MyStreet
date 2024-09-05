package ru.mystreet.uikit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.ArrowUp

@Composable
fun InfoBottomSheetScreen(
    isExpanded: Boolean,
    onDismiss: () -> Unit,
    sheetContent: @Composable () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    UIKitBottomSheetScaffold(
        isExpanded = isExpanded,
        onDismiss = onDismiss,
        sheetContent = {
            sheetContent()
        },
        sheetDragHandle = {
            DragHandle()
        },
        sheetShadowElevation = 16.dp,
        modifier = modifier,
    ) {
        content()
    }
}

@Composable
fun DragHandle(
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.primary) {
            if (onDismiss != null)
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.padding(start = 10.dp).clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onDismiss,
                    ),
                ) {
                    Text(
                        "Скрыть",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Icon(
                        UIKitIconPack.ArrowUp,
                        contentDescription = null,
                        modifier = Modifier.rotate(180f).size(18.dp),
                    )
                }
        }
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 7.5.dp),
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
            shape = CircleShape,
        ) {
            Box(
                Modifier
                    .size(
                        width = 80.dp,
                        height = 4.dp,
                    )
            )
        }

    }
}