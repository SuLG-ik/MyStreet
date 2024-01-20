package ru.mystreet.app.component.map.general

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.app.context.AppBarProvider
import ru.mystreet.app.context.BaseComponent
import ru.mystreet.app.context.DIComponentContext
import ru.mystreet.app.context.ScreenComponentContext
import ru.mystreet.app.ui.AppBar

class GeneralMapComponentContext(
    componentContext: DIComponentContext,
) : BaseComponent(componentContext), ScreenComponentContext, AppBarProvider {

    override val rootState: ScreenComponentContext.State = ScreenComponentContext.State(
        isNavigationShown = true,
        isMapShown = true
    )


    @Composable
    override fun Content(modifier: Modifier) {

    }

    @Composable
    override fun AppBarContent(modifier: Modifier) {
        AppBar(
            title = "Моя улица",
            subtitle = "Карта благоустройства",
            modifier = modifier,
        )
    }
}