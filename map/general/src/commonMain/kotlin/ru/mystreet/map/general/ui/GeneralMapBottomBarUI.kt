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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.domain.entity.GeneralLayer
import ru.mystreet.map.domain.entity.GeneralSelectedMode
import ru.mystreet.map.general.component.GeneralMapBottomBar
import ru.mystreet.uikit.UIKitTab
import ru.mystreet.uikit.UIKitTabRow
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.Settings

@Composable
fun GeneralMapBottomBarUI(
    component: GeneralMapBottomBar,
    modifier: Modifier,
) {
    val layers by component.layers.subscribeAsState()
    val selectedMode by component.selectedMode.subscribeAsState()
    GeneralMapBottomMarScreen(
        selectedMode = selectedMode,
        layers = layers,
        onLayerToggle = component::onGeneralLayerToggle,
        onModeToggle = component::onModeToggle,
        modifier = modifier,
    )
}


private fun defaultEnterAnimation(): EnterTransition {
    return fadeIn() + expandIn(expandFrom = Alignment.BottomStart)
}

private fun defaultExitAnimation(): ExitTransition {
    return fadeOut() + shrinkOut(shrinkTowards = Alignment.BottomStart)
}

@Composable
private fun GeneralMapBottomMarScreen(
    selectedMode: GeneralSelectedMode,
    layers: List<GeneralLayer>,
    onLayerToggle: (GeneralLayer) -> Unit,
    onModeToggle: (GeneralSelectedMode) -> Unit,
    modifier: Modifier,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    SideEffect {
        if (selectedMode != GeneralSelectedMode.GENERAL)
            isExpanded = false
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AnimatedVisibility(
                isExpanded,
                modifier = Modifier.align(Alignment.Start)
                    .clip(MaterialTheme.shapes.large),
                enter = defaultEnterAnimation(),
                exit = defaultExitAnimation(),
            ) {
                LayersContent(
                    layers = layers,
                    onToggleEnabled = onLayerToggle
                )
            }
            UIKitTabRow(
                selectedTabIndex = selectedMode.ordinal,
                modifier = Modifier.width(350.dp)
            ) {
                GeneralMode(
                    selectedMode = selectedMode,
                    onSelect = onModeToggle,
                    onAction = { isExpanded = !isExpanded },
                    modifier = Modifier.height(40.dp)
                )
                StreetlightMode(
                    selectedMode = selectedMode,
                    onSelect = onModeToggle,
                    modifier = Modifier.height(40.dp),
                )
                TrashMode(
                    selectedMode = selectedMode,
                    onSelect = onModeToggle,
                    modifier = Modifier.height(40.dp),
                )
            }
        }
    }
}

@Composable
private fun TrashMode(
    selectedMode: GeneralSelectedMode,
    onSelect: (GeneralSelectedMode) -> Unit,
    modifier: Modifier,
) {
    UIKitTab(
        selected = selectedMode == GeneralSelectedMode.TRASH,
        onClick = { onSelect(GeneralSelectedMode.TRASH) },
        modifier = modifier,
    ) {
        Text(
            "Урны",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun StreetlightMode(
    selectedMode: GeneralSelectedMode,
    onSelect: (GeneralSelectedMode) -> Unit,
    modifier: Modifier,
) {
    UIKitTab(
        selected = selectedMode == GeneralSelectedMode.STREETLIGHT,
        onClick = { onSelect(GeneralSelectedMode.STREETLIGHT) },
        modifier = modifier,
    ) {
        Text(
            "Освещение",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun GeneralMode(
    selectedMode: GeneralSelectedMode,
    onSelect: (GeneralSelectedMode) -> Unit,
    onAction: () -> Unit,
    modifier: Modifier,
) {
    UIKitTab(
        selected = selectedMode == GeneralSelectedMode.GENERAL,
        onClick = { onSelect(GeneralSelectedMode.GENERAL) },
        onAction = onAction,
        action = {
            Icon(
                imageVector = UIKitIconPack.Settings,
                contentDescription = null,
                modifier = Modifier.size(15.dp),
            )
        },
        modifier = modifier,
    ) {
        Text(
            "Общая",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}