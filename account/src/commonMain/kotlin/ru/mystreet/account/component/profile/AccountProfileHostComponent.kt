package ru.mystreet.account.component.profile

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildStack

class AccountProfileHostComponent(
    componentContext: DIComponentContext,
    private val onNotAuthenticated: () -> Unit,
) : AppComponentContext(componentContext), AccountProfileHost {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, AccountProfileHost.Child>> = diChildStack(
        source = navigation,
        initialConfiguration = Config.Info,
        serializer = Config.serializer(),
        childFactory = this::createChild,
    )

    private fun createChild(
        config: Config,
        diComponentContext: DIComponentContext
    ): AccountProfileHost.Child {
        return when (config) {
            Config.Info -> AccountProfileHost.Child.Info(
                AccountProfileInfoComponent(
                    diComponentContext = diComponentContext,
                    onNotAuthenticated = onNotAuthenticated,
                    onSettings = this::onSettings,
                )
            )

            Config.Settings -> AccountProfileHost.Child.Settings(
                AccountProfileSettingsComponent(
                    componentContext = diComponentContext,
                    onNotAuthenticated = onNotAuthenticated,
                    onBack = this::onBack,
                )
            )
        }
    }

    private fun onSettings() {
        navigation.bringToFront(Config.Settings)
    }

    private fun onBack() {
        navigation.pop()
    }

    @Serializable
    sealed interface Config {
        data object Info : Config
        data object Settings : Config
    }

}