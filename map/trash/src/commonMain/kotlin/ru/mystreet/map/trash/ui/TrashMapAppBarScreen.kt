package ru.mystreet.map.trash.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import ru.mystreet.uikit.MR
import ru.mystreet.uikit.UIKitAppBar

@Composable
fun TrashMapAppBarScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        UIKitAppBar(
            title = stringResource(MR.strings.app_name),
            subtitle = stringResource(MR.strings.trash_map_title),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
