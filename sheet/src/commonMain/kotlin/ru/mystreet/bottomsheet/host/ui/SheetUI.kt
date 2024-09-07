package ru.mystreet.bottomsheet.host.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import ru.mystreet.bottomsheet.child.SheetChildContent
import ru.mystreet.bottomsheet.child.component.SheetChild
import ru.mystreet.bottomsheet.host.anchor.StateSheetAnchor
import ru.mystreet.bottomsheet.host.component.SheetHost
import ru.mystreet.uikit.DragHandle
import ru.mystreet.uikit.rememberSheetState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Child : SheetChild> ModalBottomSheetUI(
    component: SheetHost<Child>,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberSheetState(component)
    ModalBottomSheet(
        onDismissRequest = remember(component) { { component.confirmValueChange(SheetValue.Hidden) } },
        sheetState = sheetState,
        modifier = modifier
    ) {
        BottomSheet(
            childContent = component.childContent,
            child = component.currentChild,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Child : SheetChild> SheetUI(
    component: SheetHost<Child>,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val sheetState = rememberSheetState(component)
    val alpha by animateFloatAsState(if (sheetState.isVisible) 1f else 0f)
    BottomSheetScaffold(
        sheetContent = {
            Box(
                modifier = Modifier.alpha(alpha)
            ) {
                BottomSheet(
                    childContent = component.childContent,
                    child = component.currentChild,
                )
            }
        },
        sheetDragHandle = {
            val currentChild = component.currentChild.subscribeAsState().value.child
            if (currentChild == null || currentChild.instance.config.hasDragHandle) {
                DragHandle(onDismiss = component::hide, modifier = Modifier.alpha(alpha))
            }
        },
        scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState),
        sheetShape = MaterialTheme.shapes.large.copy(
            bottomEnd = CornerSize(0),
            bottomStart = CornerSize(0)
        ),
        sheetShadowElevation = 16.dp,
        modifier = modifier,
    ) {
        content()
    }
}

@Composable
@ExperimentalMaterial3Api
private fun rememberSheetState(
    sheetHost: SheetHost<*>,
): SheetState {
    val state = rememberSheetState(
        skipPartiallyExpanded = false,
        initialValue = sheetHost.currentValue,
        skipHiddenState = false,
        confirmValueChange = sheetHost::confirmValueChange,
    )
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(state.currentValue) {
        if (state.currentValue == SheetValue.PartiallyExpanded) {
            if (sheetHost.forceValue == SheetValue.Expanded)
                state.expand()
            else
                state.hide()
        }
    }
    DisposableEffect(state, sheetHost) {
        sheetHost.setAnchor(StateSheetAnchor(state, coroutineScope))
        onDispose { sheetHost.clearAnchor() }
    }
    return state
}

@Composable
private fun <Child : SheetChild> BottomSheet(
    childContent: SheetChildContent<Child>,
    child: Value<ChildSlot<*, Child>>,
) {
    val currentChild by child.subscribeAsState()
    BottomSheet(childContent, currentChild)
}

@Composable
private fun <Child : SheetChild> BottomSheet(
    childContent: SheetChildContent<Child>,
    child: ChildSlot<*, Child>,
) {
    val instance = child.child?.instance
    childContent.Content(
        child = instance,
        modifier = instance?.config?.expand?.modifier ?: Modifier.fillMaxWidth()
    )
}