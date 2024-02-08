package ru.mystreet.map.general.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.map.general.component.GeneralMap

@Composable
fun GeneralMapUI(
    component: GeneralMap,
    modifier: Modifier = Modifier,
) {
    GeneralMapScreen(
        modifier = modifier
    )
}

