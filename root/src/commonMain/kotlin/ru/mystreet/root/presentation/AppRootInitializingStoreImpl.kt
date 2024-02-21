package ru.mystreet.root.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.mystreet.map.domain.entity.MapConfig
import ru.mystreet.map.domain.usecase.LoadLocalMapConfigUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class AppRootInitializingStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    savedState: AppRootInitializingStore.SavedState,
    loadLocalMapConfigUseCase: LoadLocalMapConfigUseCase,
) : AppRootInitializingStore,
    Store<AppRootInitializingStore.Intent, AppRootInitializingStore.State, AppRootInitializingStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "AppRootInitializingStoreImpl",
        initialState = savedState.toState(),
        reducer = {
            when (it) {
                is Message.SetMapConfig -> copy(isLoading = false, mapConfig = it.config)
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {
                loadLocalMapConfigUseCase().onEach {
                    if (state().isLoading)
                        publish(AppRootInitializingStore.Label.AppRootInitialized(it))
                    dispatch(Message.SetMapConfig(it))
                }.flowOn(Dispatchers.Main)
                    .launchIn(this)
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetMapConfig(val config: MapConfig) : Message
    }

    override fun getSavedState(): AppRootInitializingStore.SavedState {
        return AppRootInitializingStore.SavedState(
            mapConfig = state.mapConfig,
        )
    }
}

private fun AppRootInitializingStore.SavedState.toState(): AppRootInitializingStore.State {
    return AppRootInitializingStore.State(
        isLoading = mapConfig == null,
        mapConfig = mapConfig,
    )
}