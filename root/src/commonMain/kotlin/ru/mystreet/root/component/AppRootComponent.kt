package ru.mystreet.root.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.rx.Observer
import kotlinx.serialization.Serializable
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.diChildStack
import ru.mystreet.core.component.getSavedStateStore
import ru.mystreet.core.component.values
import ru.mystreet.map.domain.entity.MapConfig
import ru.mystreet.map.root.component.MapHostComponent
import ru.mystreet.root.presentation.AppRootInitializingStore

class AppRootComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), AppRoot {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, AppRoot.Child>> = diChildStack(
        source = navigation,
        initialConfiguration = Config.Initializing,
        serializer = Config.serializer(),
        childFactory = this::createChild,
    )

    private val store: AppRootInitializingStore = getSavedStateStore(
        key = "app_root_initialer",
        initialSavedState = { AppRootInitializingStore.SavedState() },
    )

    override val isInitializing: Value<Boolean> = store.values(this).map { it.isLoading }

    init {
        val disposable = store.labels(object : Observer<AppRootInitializingStore.Label> {
            override fun onComplete() {

            }

            override fun onNext(value: AppRootInitializingStore.Label) {
                when (value) {
                    is AppRootInitializingStore.Label.AppRootInitialized -> onMapConfigLoaded(value.mapConfig)
                }
            }
        })
        lifecycle.doOnDestroy(disposable::dispose)
    }

    private fun createChild(
        config: Config,
        diComponentContext: DIComponentContext,
    ): AppRoot.Child {
        return when (config) {
            Config.Initializing -> AppRoot.Child.Initializing
            is Config.MapHost -> AppRoot.Child.MapHost(
                MapHostComponent(
                    componentContext = diComponentContext,
                    mapConfig = config.config,
                )
            )
        }
    }

    private fun onMapConfigLoaded(mapConfig: MapConfig) {
        navigation.replaceAll(Config.MapHost(mapConfig))
    }

    @Serializable
    sealed interface Config {

        @Serializable
        data class MapHost(val config: MapConfig) : Config

        @Serializable
        data object Initializing : Config

    }

}
