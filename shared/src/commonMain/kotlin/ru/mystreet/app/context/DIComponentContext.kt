package ru.mystreet.app.context

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigationSource
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.statekeeper.SerializableContainer
import com.arkivanov.essenty.statekeeper.consumeRequired
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import org.koin.core.component.KoinComponent
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope

abstract class BaseComponent(componentContext: DIComponentContext) :
    DIComponentContext by componentContext, UIComponentContext

interface UIComponentContext {

    @Composable
    fun Content(modifier: Modifier)

}

interface DIComponentContext : ComponentContext, KoinComponent

abstract class RootComponent(
    componentContext: DIComponentContext,
) : DIComponentContext by componentContext {

    @Composable
    abstract fun RootContent()

}

private class DefaultDIComponentContext(
    componentContext: ComponentContext,
) : DIComponentContext, ComponentContext by componentContext

fun ComponentContext.withDI(): DIComponentContext {
    return DefaultDIComponentContext(this)
}

fun DIComponentContext.diChildContext(
    key: String,
    lifecycle: Lifecycle? = null
): DIComponentContext {
    return childContext(key, lifecycle).withDI()
}

fun <C : Any, T : Any> DIComponentContext.diChildStack(
    source: StackNavigationSource<C>,
    serializer: KSerializer<C>?,
    initialStack: () -> List<C>,
    key: String = "DefaultChildStack",
    handleBackButton: Boolean = false,
    childFactory: (configuration: C, DIComponentContext) -> T,
): Value<ChildStack<C, T>> =
    childStack(
        source = source,
        saveStack = { stack ->
            if (serializer != null) {
                SerializableContainer(value = stack, strategy = ListSerializer(serializer))
            } else {
                null
            }
        },
        restoreStack = { container ->
            if (serializer != null) {
                container.consumeRequired(strategy = ListSerializer(serializer))
            } else {
                null
            }
        },
        initialStack = initialStack,
        key = key,
        handleBackButton = handleBackButton,
        childFactory = { config, context ->
            childFactory(config, context.withDI())
        },
    )

fun <C : Any, T : Any> DIComponentContext.diChildStack(
    source: StackNavigationSource<C>,
    serializer: KSerializer<C>?,
    initialConfiguration: C,
    key: String = "DefaultChildStack",
    handleBackButton: Boolean = false,
    childFactory: (configuration: C, DIComponentContext) -> T
): Value<ChildStack<C, T>> =
    diChildStack(
        source = source,
        serializer = serializer,
        initialStack = { listOf(initialConfiguration) },
        key = key,
        handleBackButton = handleBackButton,
        childFactory = childFactory,
    )