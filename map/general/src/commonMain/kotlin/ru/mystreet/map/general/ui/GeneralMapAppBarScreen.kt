package ru.mystreet.map.general.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import ru.mystreet.map.domain.entity.GeneralLayer
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
    layers: List<GeneralLayer>,
    onLayerToggle: (GeneralLayer) -> Unit,
    onEditModeToggle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
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
            endActions = {
                LayersButton(
                    isSelected = isExpanded,
                    toggleSelected = { isExpanded = !isExpanded },
                    modifier = Modifier.alpha(DefaultMapAlpha)
                        .size(UIKitSizeTokens.DefaultIconButtonSize),
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )
        AnimatedVisibility(
            isExpanded,
            modifier = Modifier.align(Alignment.End)
                .clip(MaterialTheme.shapes.large),
            enter = defaultEnterAnimation(),
            exit = defaultExitAnimation(),
        ) {
            LayersContent(
                layers = layers,
                onToggleEnabled = onLayerToggle
            )
        }
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


private fun defaultEnterAnimation(): EnterTransition {
    return fadeIn() + expandIn(expandFrom = Alignment.TopEnd) + scaleIn(
        transformOrigin = TransformOrigin(
            1f,
            0f
        ),
        initialScale = 0.8f,
    )
}

private fun defaultExitAnimation(): ExitTransition {
    return fadeOut() + shrinkOut(shrinkTowards = Alignment.TopEnd) + scaleOut(
        transformOrigin = TransformOrigin(
            1f,
            0f
        ), targetScale = 0.8f
    )
}