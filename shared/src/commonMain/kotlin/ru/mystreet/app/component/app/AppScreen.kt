package ru.mystreet.app.component.app

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mystreet.app.context.AppBarProvider
import ru.mystreet.app.context.DIComponentContext
import ru.mystreet.app.component.map.Map
import ru.mystreet.app.context.BaseComponent
import ru.mystreet.app.ui.BottomAppBar
import ru.mystreet.app.ui.NavigationBarItem
import ru.mystreet.app.ui.animation.topBarSlide
import ru.mystreet.app.utils.max


@Composable
fun AppScreen(
    state: AppHost.State,
    map: Map,
    childStack: Value<ChildStack<AppHost.Config, BaseComponent>>,
    onNavigate: (AppHost.Config) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            ChildrenTopBar(
                childStack = childStack,
                modifier = Modifier.padding(ScaffoldDefaults.contentWindowInsets.asPaddingValues())
            )
        },
        bottomBar = {
            Navigation(
                childStack = childStack,
                onNavigate = onNavigate,
                modifier = Modifier.padding(
                    max(
                        ScaffoldDefaults.contentWindowInsets.asPaddingValues(),
                        PaddingValues(bottom = 20.dp)
                    )
                ).graphicsLayer(alpha = 0.9f),
            )
        },
        modifier = modifier,
    ) {
        Map(
            state = state,
            map = map,
            modifier = Modifier.fillMaxSize().padding(it).padding(horizontal = 5.dp),
        )
        ChildrenContent(
            childStack = childStack,
        )
    }
}

@Composable
private fun ChildrenContent(
    childStack: Value<ChildStack<AppHost.Config, BaseComponent>>,
    modifier: Modifier = Modifier
) {
    Children(childStack, modifier = modifier) {
        val instance = it.instance
        instance.Content(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun ChildrenTopBar(
    childStack: Value<ChildStack<*, DIComponentContext>>,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = childStack,
        modifier = modifier,
        animation = stackAnimation(topBarSlide(tween(500)) + fade(tween(200)))
    ) {
        val instance = it.instance
        if (instance is AppBarProvider) {
            instance.AppBarContent(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun Map(
    state: AppHost.State, map: Map, modifier: Modifier = Modifier
) {
    if (state.map.isMapShown) {
        map.Content(
            modifier = modifier,
        )
    }
}

@Composable
private fun Navigation(
    childStack: Value<ChildStack<AppHost.Config, DIComponentContext>>,
    onNavigate: (AppHost.Config) -> Unit,
    modifier: Modifier = Modifier,
) {
    val activeChildren = childStack.subscribeAsState().value.active
    BottomAppBar(
        modifier = modifier.padding(horizontal = 15.dp, vertical = 5.dp),
    ) {
        AppHost.Config.allConfig.forEach {
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = rememberVectorPainter(image = it.icon), contentDescription = null
                    )
                },
                onClick = { onNavigate(it) },
                selected = it == activeChildren.configuration,
            )
        }
    }
}