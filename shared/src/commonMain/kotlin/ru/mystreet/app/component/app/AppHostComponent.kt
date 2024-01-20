package ru.mystreet.app.component.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import ru.mystreet.app.context.ScreenComponentContext
import ru.mystreet.app.component.map.Map
import ru.mystreet.app.component.map.MapComponent
import ru.mystreet.app.component.map.general.GeneralMapComponentContext
import ru.mystreet.app.context.BaseComponent
import ru.mystreet.app.context.DIComponentContext
import ru.mystreet.app.context.diChildContext
import ru.mystreet.app.context.diChildStack
import ru.mystreet.app.resources.IconPack
import ru.mystreet.app.resources.iconpack.Home
import ru.mystreet.app.resources.iconpack.Parks
import ru.mystreet.app.resources.iconpack.Search
import ru.mystreet.app.resources.iconpack.Trash

class AppHostComponent(
    componentContext: DIComponentContext,
) : BaseComponent(componentContext), AppHost {

    override val state: AppHost.State = AppHost.State(AppHost.State.Map(true))

    override val map: Map = MapComponent(diChildContext("map_kit_component"))

    private val navigation = StackNavigation<AppHost.Config>()

    override val childStack = diChildStack(
        source = navigation,
        serializer = AppHost.Config.serializer(),
        initialConfiguration = AppHost.Config.General,
        childFactory = this::createChild,
    )

    private fun createChild(
        config: AppHost.Config,
        componentContext: DIComponentContext
    ): BaseComponent {
        return when (config) {
            AppHost.Config.General -> GeneralMapComponentContext(componentContext)
            AppHost.Config.Parks -> GeneralMapComponentContext(componentContext)
            AppHost.Config.Search -> GeneralMapComponentContext(componentContext)
            AppHost.Config.Trash -> GeneralMapComponentContext(componentContext)
        }
    }

    override fun onNavigate(config: AppHost.Config) {
        navigation.bringToFront(config)
    }

    @Composable
    override fun Content(modifier: Modifier) {
        AppScreen(
            state = state,
            map = map,
            childStack = childStack,
            onNavigate = this::onNavigate,
            modifier = modifier,
        )
    }

}