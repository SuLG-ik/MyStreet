package ru.mystreet.app.feature.dialogs.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.app.feature.dialogs.component.DialogComponent
import ru.mystreet.app.feature.dialogs.component.modal.ModalDialogComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDialogUI(
    component: DialogComponent,
    modifier: Modifier = Modifier,
    containerColor: Color = BottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: @Composable () -> WindowInsets = { BottomSheetDefaults.windowInsets },
    content: @Composable () -> Unit,
) {
    CustomDialogUI(component) {
        ModalBottomSheet(
            onDismissRequest = component::onDismiss,
            modifier = modifier,
            containerColor = containerColor,
            contentColor = contentColor,
            contentWindowInsets = contentWindowInsets,
            dragHandle = null,
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDialogUI(
    component: ModalDialogComponent,
    modifier: Modifier = Modifier,
    containerColor: Color = BottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: @Composable () -> WindowInsets = { BottomSheetDefaults.windowInsets },
    content: @Composable () -> Unit,
) {
    val state by component.state.subscribeAsState()
    val sheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = state.skipPartiallyExpanded)
    CustomDialogUI(component) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = component::onDismiss,
            modifier = modifier,
            containerColor = containerColor,
            contentColor = contentColor,
            contentWindowInsets = contentWindowInsets,
            dragHandle = null,
        ) {
            content()
        }
    }
}
