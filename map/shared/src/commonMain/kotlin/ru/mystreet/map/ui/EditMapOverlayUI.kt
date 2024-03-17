package ru.mystreet.map.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.component.EditMap

@Composable
fun EditMapOverlayUI(
    component: EditMap,
    modifier: Modifier,
) {
    val isEnabled by component.isEnabled.subscribeAsState()
    EditMapOverlay(
        isShown = isEnabled,
        modifier = modifier,
    )
}