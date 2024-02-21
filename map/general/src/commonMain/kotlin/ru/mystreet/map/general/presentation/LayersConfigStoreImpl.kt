package ru.mystreet.map.general.presentation

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
import kotlinx.coroutines.launch
import ru.mystreet.map.domain.entity.GeneralLayer
import ru.mystreet.map.general.domain.usecase.GeneralLayersConfigFlowUseCase
import ru.mystreet.map.general.domain.usecase.UpdateGeneralLayerConfigUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class LayersConfigStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    updateGeneralLayerConfigUseCase: UpdateGeneralLayerConfigUseCase,
    generalLayersConfigFlowUseCase: GeneralLayersConfigFlowUseCase,
) : LayersConfigStore,
    Store<LayersConfigStore.Intent, LayersConfigStore.State, LayersConfigStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "LayersConfigStoreImpl",
        initialState = LayersConfigStore.State(),
        reducer = {
            when (it) {
                is Message.UpdateLayers -> copy(it.layers)
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {
                generalLayersConfigFlowUseCase()
                    .onEach { dispatch(Message.UpdateLayers(it)) }
                    .flowOn(Dispatchers.Main)
                    .launchIn(this)
            }
            onIntent<LayersConfigStore.Intent.UpdateLayerEnabled> {
                launch {
                    updateGeneralLayerConfigUseCase(it.layer, it.isEnabled)
                }
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class UpdateLayers(
            val layers: List<GeneralLayer>,
        ) : Message
    }
}