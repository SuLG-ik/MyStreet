package ru.mystreet.map.root.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mystreet.uikit.UIKitBottomSheetScaffold

@Composable
fun MapInfoScreen(
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
) {
    Surface(
        modifier = modifier
            .padding(vertical = 5.dp),
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