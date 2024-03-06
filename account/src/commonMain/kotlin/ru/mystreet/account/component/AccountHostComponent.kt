package ru.mystreet.account.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.mystreet.account.component.auth.AccountAuthHostComponent
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
        initialConfiguration = Config.Auth,
        childFactory = this::createChild,
    )

    private fun createChild(
        config: Config,
        diComponentContext: DIComponentContext,
    ): AccountHost.Child {
        return when (config) {
            Config.Auth -> AccountHost.Child.Auth(AccountAuthHostComponent(diComponentContext))
        }
    }

    override val isExpanded: MutableValue<Boolean> =
        savedValue("is_expanded", defaultValue = { false })

    override fun onDismiss() {
        isExpanded.value = false
    }

    override fun onExpand() {
        isExpanded.value = true
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Auth : Config
    }

}