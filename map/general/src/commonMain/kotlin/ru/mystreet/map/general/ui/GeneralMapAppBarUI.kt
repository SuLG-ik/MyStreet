package ru.mystreet.map.general.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.general.component.GeneralMapAppBar

@Composable
fun GeneralMapAppBarUI(
    component: GeneralMapAppBar,
    modifier: Modifier = Modifier,
) {
    val layers by component.layers.subscribeAsState()
    GeneralMapAppBarScreen(
        layers = layers,
        onToggle = component::onToggleEnabled,
        modifier = modifier,
    )
}
