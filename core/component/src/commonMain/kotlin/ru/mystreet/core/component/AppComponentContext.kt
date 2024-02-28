package ru.mystreet.core.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.children.NavigationSource
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.statekeeper.SerializableContainer
import com.arkivanov.essenty.statekeeper.consumeRequired
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import org.koin.core.component.KoinComponent

abstract class AppComponentContext(componentContext: DIComponentContext) :
    DIComponentContext by componentContext

interface DIComponentContext : ComponentContext, KoinComponent

private class DefaultDIComponentContext(
    componentContext: ComponentContext,
) : DIComponentContext, ComponentContext by componentContext

fun ComponentContext.withDI(): DIComponentContext {
    return DefaultDIComponentContext(this)
}

fun DIComponentContext.diChildContext(
    key: String,
    lifecycle: Lifecycle? = null,
): DIComponentContext {
    return childContext(key, lifecycle).withDI()
}

fun <C : Any, T : Any> DIComponentContext.diChildStack(
    source: NavigationSource<StackNavigation.Event<C>>,
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
    source: NavigationSource<StackNavigation.Event<C>>,
    serializer: KSerializer<C>?,
    initialConfiguration: C,
    key: String = "DefaultChildStack",
    handleBackButton: Boolean = false,
    childFactory: (configuration: C, DIComponentContext) -> T,
): Value<ChildStack<C, T>> =
    diChildStack(
        source = source,
        serializer = serializer,
        initialStack = { listOf(initialConfiguration) },
        key = key,
        handleBackButton = handleBackButton,
        childFactory = childFactory,
    )

fun <C : Any, T : Any> ComponentContext.diChildSlot(
    source: NavigationSource<SlotNavigation.Event<C>>,
    serializer: KSerializer<C>?,
    initialConfiguration: () -> C? = { null },
    key: String = "DefaultChildSlot",
    handleBackButton: Boolean = false,
    childFactory: (configuration: C, DIComponentContext) -> T,
): Value<ChildSlot<C, T>> =
    childSlot(
        source = source,
        saveConfiguration = { configuration ->
            if ((serializer != null) && (configuration != null)) {
                SerializableContainer(value = configuration, strategy = serializer)
            } else {
                null
            }
        },
        restoreConfiguration = { container ->
            if (serializer != null) {
                container.consumeRequired(strategy = serializer)
            } else {
                null
            }
        },
        key = key,
        initialConfiguration = initialConfiguration,
        handleBackButton = handleBackButton,
        childFactory = { configuration, componentContext ->
            childFactory(configuration, componentContext.withDI())
        },
    )