package ru.mystreet.map.parks.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.map.parks.component.ParksMap

@Composable
fun ParksMapUI(
    component: ParksMap,
    modifier: Modifier = Modifier,
) {
    ParksMapScreen(
        modifier = modifier
    )
}

