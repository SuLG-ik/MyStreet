package ru.mystreet.map.trash.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.trash.component.TrashMapAppBar

@Composable
fun TrashMapAppBarUI(
    component: TrashMapAppBar,
    modifier: Modifier = Modifier,
) {
    TrashMapAppBarScreen(
        modifier = modifier,
    )
}
