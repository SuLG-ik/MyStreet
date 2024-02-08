package ru.mystreet.map.trash.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.map.trash.component.TrashMap

@Composable
fun TrashMapUI(
    component: TrashMap,
    modifier: Modifier = Modifier,
) {
    TrashMapScreen(
        modifier = modifier
    )
}

