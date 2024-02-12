package ru.mystreet.map.general.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import ru.mystreet.map.domain.entity.GeneralLayer
import ru.mystreet.uikit.DefaultMapAlpha
import ru.mystreet.uikit.MR
import ru.mystreet.uikit.UIKitSelectableTonalIconButton
import ru.mystreet.uikit.tokens.UIKitSizeTokens

@Composable
fun LayersButton(
    isSelected: Boolean,
    toggleSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    UIKitSelectableTonalIconButton(
        selected = isSelected,
        onClick = toggleSelected,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
    ) {
        Icon(
            painterResource(MR.images.layers),
            contentDescription = null,
            modifier = Modifier.size(UIKitSizeTokens.DefaultIconSize),
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LayersContent(
    layers: List<GeneralLayer>,
    onToggleEnabled: (GeneralLayer) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        modifier = modifier.alpha(DefaultMapAlpha),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            LayerItems(
                layers = layers,
                onToggleEnabled = onToggleEnabled,
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 20.dp
                )
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LayerItems(
    layers: List<GeneralLayer>,
    onToggleEnabled: (GeneralLayer) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        maxItemsInEachRow = 4,
        modifier = modifier,
    ) {
        layers.forEach {
            LayerItem(
                isEnabled = it.isEnabled,
                layerInfo = it.type.toUI(),
                onToggleEnabled = { onToggleEnabled(it) },
                modifier = Modifier.width(70.dp)
            )
        }
    }
}

@Composable
private fun LayerItem(
    isEnabled: Boolean,
    layerInfo: LayerInfo,
    onToggleEnabled: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LayerItem(
        isEnabled = isEnabled,
        onToggleEnabled = onToggleEnabled,
        label = stringResource(layerInfo.title),
        image = painterResource(layerInfo.image),
        modifier = modifier,
    )
}


@Composable
fun LayerItem(
    isEnabled: Boolean,
    onToggleEnabled: () -> Unit,
    label: String,
    image: Painter,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onToggleEnabled,
        )
    ) {
        UIKitSelectableTonalIconButton(
            selected = isEnabled,
            onClick = onToggleEnabled,
            unselectedColor = MaterialTheme.colorScheme.secondaryContainer,
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 0.dp,
            modifier = Modifier.size(UIKitSizeTokens.HigherIconButtonSize),
            interactionSource = interactionSource
        ) {
            Icon(
                painter = image,
                contentDescription = null,
                modifier = Modifier.size(UIKitSizeTokens.DefaultIconSize),
            )
        }
        Text(
            label,
            style = MaterialTheme.typography.labelSmall,
            overflow = TextOverflow.Visible,
            maxLines = 1
        )
    }
}