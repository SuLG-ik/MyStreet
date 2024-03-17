package ru.mystreet.account.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.mystreet.account.component.auth.AccountAuthHostComponent
import ru.mystreet.account.component.profile.AccountProfileHostComponent
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildStack
import ru.mystreet.core.component.savedValue

class AccountHostComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), AccountHost {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, AccountHost.Child>> = diChildStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Profile,
        childFactory = this::createChild,
    )

    private fun createChild(
        config: Config,
        diComponentContext: DIComponentContext,
    ): AccountHost.Child {
        return when (config) {
            Config.Auth -> AccountHost.Child.Auth(
                AccountAuthHostComponent(
                    componentContext = diComponentContext,
                    onBack = this::onBack,
                    onAuthenticated = this::onAuthenticated,
                )
            )

            Config.Profile -> AccountHost.Child.Profile(
                AccountProfileHostComponent(
                    componentContext = diComponentContext,
                    onNotAuthenticated = this::onNotAuthenticated
                )
            )
        }
    }

    private fun onAuthenticated() {
        navigation.bringToFront(Config.Profile)
    }

    private fun onNotAuthenticated() {
        navigation.bringToFront(Config.Auth)
    }

    override val isExpanded: MutableValue<Boolean> =
        savedValue("is_expanded", defaultValue = { false })

    override fun onDismiss() {
        isExpanded.value = false
    }

    override fun onExpand() {
        isExpanded.value = true
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