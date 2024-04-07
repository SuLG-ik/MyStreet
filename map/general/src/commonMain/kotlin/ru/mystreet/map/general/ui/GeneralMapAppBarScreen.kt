package ru.mystreet.map.general.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import ru.mystreet.uikit.DefaultMapAlpha
import ru.mystreet.uikit.MR
import ru.mystreet.uikit.UIKitAppBar
import ru.mystreet.uikit.UIKitSelectableTonalIconButton
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.EditIcon
import ru.mystreet.uikit.tokens.UIKitSizeTokens

@Composable
fun GeneralMapAppBarScreen(
    isInEditMode: Boolean,
    onEditModeToggle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        UIKitAppBar(
            title = stringResource(MR.strings.app_name),
            subtitle = stringResource(MR.strings.general_map_title),
            startActions = {
                EditButton(
                    isInEditMode = isInEditMode,
                    onToggleMode = onEditModeToggle,
                    modifier = Modifier.alpha(DefaultMapAlpha)
                        .size(UIKitSizeTokens.DefaultIconButtonSize),
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun EditButton(
    isInEditMode: Boolean,
    onToggleMode: () -> Unit,
    modifier: Modifier,
) {
    UIKitSelectableTonalIconButton(
        selected = isInEditMode,
        onClick = onToggleMode,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
    ) {
        Icon(
            UIKitIconPack.EditIcon,
            contentDescription = null,
            modifier = Modifier.size(UIKitSizeTokens.DefaultIconSize)
        )
    }
}
