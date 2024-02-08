package ru.mystreet.map.parks.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.map.parks.component.ParksMapAppBar

@Composable
fun ParksMapAppBarUI(
    component: ParksMapAppBar,
    modifier: Modifier = Modifier,
) {
    ParksMapAppBarScreen(
        modifier = modifier,
    )
}
