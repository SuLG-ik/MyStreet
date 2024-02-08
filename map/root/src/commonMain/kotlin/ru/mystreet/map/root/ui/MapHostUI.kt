package ru.mystreet.map.root.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import ru.mystreet.uikit.animation.topBarSlide
import ru.mystreet.uikit.paddingHorizontalInsets
import ru.mystreet.uikit.paddingVerticalInsets

@Composable
fun MapHostUI(
    component: MapHost,
    modifier: Modifier = Modifier,
) {
    val currentChild by component.childStack.subscribeAsState()
    MapHostScreen(
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
        map = {
            MapUI(
                component.map,
                modifier = Modifier.fillMaxSize().paddingVerticalInsets().paddingHorizontalInsets()
            )
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