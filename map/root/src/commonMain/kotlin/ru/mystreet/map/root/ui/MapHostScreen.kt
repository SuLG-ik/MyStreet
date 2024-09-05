package ru.mystreet.map.root.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import ru.mystreet.map.root.component.MapHost
import ru.mystreet.uikit.DefaultMapAlpha
import ru.mystreet.uikit.UIKitBottomAppBar
import ru.mystreet.uikit.UIKitNavigationBarItem
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.AccountIcon
import ru.mystreet.uikit.iconpack.uikiticonpack.Home
import ru.mystreet.uikit.iconpack.uikiticonpack.Parks

enum class MapHostNavItem(
    val icon: ImageVector,
    val config: MapHost.Config,
) {
    GENERAL(UIKitIconPack.Home, MapHost.Config.General),
//    PARKS(UIKitIconPack.Parks, MapHost.Config.Parks),
    //    TRASH(UIKitIconPack.Trash, MapHost.Config.Trash),
    //    SEARCH(UIKitIconPack.Search, MapHost.Config.Search),

    ACCOUNT(UIKitIconPack.AccountIcon, MapHost.Config.Account),
}

@Composable
fun MapHostScreen(
    isBottomBarVisible: Boolean,
    currentConfig: MapHost.Config,
    onNavigate: (MapHost.Config) -> Unit,
    childTopBar: @Composable () -> Unit,
    childBottomBar: @Composable () -> Unit,
    map: @Composable () -> Unit,
    bottomBarOverlay: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = childTopBar,
        bottomBar = {
            BottomNavigation(
                isBottomBarVisible = isBottomBarVisible,
                currentConfig = currentConfig,
                onNavigate = onNavigate,
                childBottomBar = childBottomBar
            )
        },
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            map()
            bottomBarOverlay()
        }
    }
}

@Composable
private fun BottomNavigation(
    isBottomBarVisible: Boolean,
    currentConfig: MapHost.Config,
    onNavigate: (MapHost.Config) -> Unit,
    childBottomBar: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        AnimatedVisibility(
            isBottomBarVisible,
            enter = fadeIn() + slideInVertically { it },
            exit = fadeOut() + slideOutVertically { it }
        ) {
            Column {
                childBottomBar()
                Navigation(
                    currentItem = currentConfig.toUI(),
                    allItems = enumValues<MapHostNavItem>(),
                    onNavigate = { onNavigate(it.config) },
                    modifier = Modifier
                        .clickable(false) {}
                        .graphicsLayer(alpha = DefaultMapAlpha),
                )
            }
        }
    }
}

@Composable
private fun Navigation(
    currentItem: MapHostNavItem,
    allItems: Array<MapHostNavItem>,
    onNavigate: (MapHostNavItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    UIKitBottomAppBar(
        modifier = modifier.padding(horizontal = 15.dp, vertical = 20.dp),
    ) {
        allItems.forEach {
            UIKitNavigationBarItem(
                icon = {
                    Icon(
                        painter = rememberVectorPainter(image = it.icon), contentDescription = null
                    )
                },
                onClick = { onNavigate(it) },
                selected = it == currentItem,
            )
        }
    }
}

private fun MapHost.Config.toUI(): MapHostNavItem {
    return when (this) {
        MapHost.Config.General -> MapHostNavItem.GENERAL
        MapHost.Config.Parks -> TODO("MapHostNavItem.PARKS")
        MapHost.Config.Search -> TODO("MapHostNavItem.SEARCH")
        MapHost.Config.Account -> MapHostNavItem.ACCOUNT
    }
}
