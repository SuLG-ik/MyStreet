package ru.mystreet.account.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.mystreet.account.component.auth.AccountAuthHostComponent
import ru.mystreet.account.component.profile.AccountProfileHostComponent
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildStack

class DefaultAccountHostComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), AccountHostComponent {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, AccountHostComponent.Child>> = diChildStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Profile,
        childFactory = this::createChild,
    )

    private fun createChild(
        config: Config,
        diComponentContext: DIComponentContext,
    ): AccountHostComponent.Child {
        return when (config) {
            Config.Auth -> AccountHostComponent.Child.Auth(
                AccountAuthHostComponent(
                    componentContext = diComponentContext,
                    onBack = this::onBack,
                    onAuthenticated = this::onAuthenticated,
                )
            )

            Config.Profile -> AccountHostComponent.Child.Profile(
                AccountProfileHostComponent(
                    componentContext = diComponentContext,
                    onNotAuthenticated = this::onNotAuthenticated
                )
            )
        }
    }

    private fun onAuthenticated() {
        navigation.replaceAll(Config.Profile)
    }

    private fun onNotAuthenticated() {
        navigation.replaceAll(Config.Auth)
    }

    private fun onBack() {

    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Auth : Config

        @Serializable
        data object Profile : Config
    }

}