package ru.mystreet.map.root.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        modifier = modifier,
    ) {
        content()
    }
}
