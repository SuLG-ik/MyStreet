package ru.mystreet.app.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mystreet.app.component.app.AppHost
import ru.mystreet.app.component.app.AppHostComponent
import ru.mystreet.app.context.DIComponentContext
import ru.mystreet.app.context.RootComponent
import ru.mystreet.app.theme.MyStreetApplicationTheme

internal class MyStreetAppComponent(
    componentContext: DIComponentContext,
) : RootComponent(componentContext) {

    private val appHost: AppHost = AppHostComponent(componentContext)

    @Composable
    private fun Content(modifier: Modifier) {
        MyStreetApplicationTheme {
            Surface(
                modifier = Modifier.consumeWindowInsets(WindowInsets.safeContent)
            ) {
                appHost.Content(modifier = modifier)
            }
        }
    }

    @Composable
    override fun RootContent() {
        Content(
            modifier = Modifier.fillMaxSize()
        )
    }

}
