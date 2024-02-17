package ru.mystreet.map.root.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.map.general.ui.GeneralMapAppBarUI
import ru.mystreet.map.map.ui.MapUI
import ru.mystreet.map.parks.ui.ParksMapAppBarUI
import ru.mystreet.map.root.component.MapHost
import ru.mystreet.map.trash.ui.TrashMapAppBarUI
import ru.mystreet.map.ui.EditMapBottomBarUI
import ru.mystreet.map.ui.EditMapOverlayUI
import ru.mystreet.uikit.DefaultMapAlpha
import ru.mystreet.uikit.animation.topBarSlide
import ru.mystreet.uikit.paddingHorizontalInsets
import ru.mystreet.uikit.paddingVerticalInsets

@Composable
fun MapHostUI(
    component: MapHost,
    modifier: Modifier = Modifier,
) {
    val currentChild by component.childStack.subscribeAsState()
    val uiConfig by component.uiConfig.subscribeAsState()
    MapHostScreen(
        isBottomBarVisible = uiConfig.isBottomBarVisible,
        currentConfig = currentChild.active.configuration,
        onNavigate = component::onNavigate,
        modifier = modifier,
        childTopBar = {
            Children(currentChild, animation = stackAnimation(topBarSlide() + fade())) {
                MapHostAppBarChildren(
                    child = it.instance,
                    modifier = Modifier.paddingVerticalInsets().fillMaxWidth()
                )
            }
        },
        bottomBarOverlay = {
            EditMapBottomBarUI(
                component = component.editMap.bottomBar,
                modifier = Modifier.fillMaxWidth()
                    .alpha(DefaultMapAlpha)
                    .padding(WindowInsets.ime.asPaddingValues())
            )
        },
        map = {
            Box {
                MapUI(
                    component.map,
                    modifier = Modifier.fillMaxSize().paddingVerticalInsets()
                        .paddingHorizontalInsets()
                )
                EditMapOverlayUI(
                    component.editMap,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    )
}

@Composable
fun MapHostAppBarChildren(
    child: MapHost.Child,
    modifier: Modifier,
) {
    when (child) {
        is MapHost.Child.General -> GeneralMapAppBarUI(child.component.appBar, modifier)
        is MapHost.Child.Parks -> ParksMapAppBarUI(child.component.appBar, modifier)
        MapHost.Child.Search -> TODO()
        is MapHost.Child.Trash -> TrashMapAppBarUI(child.component.appBar, modifier)
    }
}