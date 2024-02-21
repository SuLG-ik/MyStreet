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
    val isInEditMode by component.isInEditMode.subscribeAsState()
    val layers by component.layers.subscribeAsState()
    GeneralMapAppBarScreen(
        isInEditMode = isInEditMode,
        layers = layers,
        onLayerToggle = component::onLayerToggle,
        onEditModeToggle = component::onEditModeToggle,
        modifier = modifier,
    )
}
