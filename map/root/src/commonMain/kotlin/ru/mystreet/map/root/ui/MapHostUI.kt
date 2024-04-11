package ru.mystreet.map.root.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.mystreet.bottomsheet.host.ui.SheetUI
import ru.mystreet.errors.ui.ErrorsListUI
import ru.mystreet.map.general.ui.GeneralMapAppBarUI
import ru.mystreet.map.general.ui.GeneralMapBottomBarUI
import ru.mystreet.map.map.ui.MapUI
import ru.mystreet.map.parks.ui.ParksMapAppBarUI
import ru.mystreet.map.root.component.MapHost
import ru.mystreet.map.trash.ui.TrashMapAppBarUI
import ru.mystreet.map.ui.EditMapBottomBarUI
import ru.mystreet.map.ui.EditMapOverlayUI
import ru.mystreet.uikit.DefaultMapAlpha
import ru.mystreet.uikit.animation.bottomBarSlide
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
    ErrorsListUI(
        component = component.errorsList,
        modifier = Modifier.fillMaxWidth().paddingVerticalInsets()
    ) {
        SheetUI(
            component = component.sheetHost,
            modifier = Modifier.fillMaxSize(),
        ) {
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
                childBottomBar = {
                    Children(currentChild, animation = stackAnimation(bottomBarSlide() + fade())) {
                        MapHostBottomBarChildren(
                            child = it.instance,
                            modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth()
                                .alpha(DefaultMapAlpha)
                        )
                    }
                },
                bottomBarOverlay = {
                    EditMapBottomBarUI(
                        component = component.editMap.bottomBar,
                        modifier = Modifier.fillMaxWidth()
                            .alpha(DefaultMapAlpha)
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
    }
}

@Composable
fun MapHostAppBarChildren(
    child: MapHost.Child,
    modifier: Modifier,
) {
    when (child) {
        is MapHost.Child.General -> GeneralMapAppBarUI(child.component.appBar, modifier)
        is MapHost.Child.Parks -> ParksMapAppBarUI(child.component.appBar, modifier)
        is MapHost.Child.Trash -> TrashMapAppBarUI(child.component.appBar, modifier)
    }
}

@Composable
fun MapHostBottomBarChildren(
    child: MapHost.Child,
    modifier: Modifier,
) {
    when (child) {
        is MapHost.Child.General -> GeneralMapBottomBarUI(child.component.bottomBar, modifier)
        else -> {}
    }
}