package ru.mystreet.account.component.auth

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildStack

class AccountAuthHostComponent(
    componentContext: DIComponentContext
) : AppComponentContext(componentContext), AccountAuthHost {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, AccountAuthHost.Child>> = diChildStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Login,
        childFactory = this::createChild,
    )

    private fun createChild(
        config: Config,
        diComponentContext: DIComponentContext,
    ): AccountAuthHost.Child {
        return when(config) {
            Config.Login -> AccountAuthHost.Child.Login(AccountLoginComponent(diComponentContext))
        }
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Login: Config
    }

}