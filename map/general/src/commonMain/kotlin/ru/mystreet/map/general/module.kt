package ru.mystreet.map.general

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.map.general.domain.usecase.GeneralLayersConfigFlowUseCase
import ru.mystreet.map.general.domain.usecase.SelectedModeConfigFlowUseCase
import ru.mystreet.map.general.domain.usecase.UpdateGeneralLayerConfigUseCase
import ru.mystreet.map.general.domain.usecase.UpdateSelectedModeConfigUseCase
import ru.mystreet.map.general.presentation.LayersConfigStore
import ru.mystreet.map.general.presentation.LayersConfigStoreImpl

val mapGeneralModule = module {
    factoryOf(::LayersConfigStoreImpl) bind LayersConfigStore::class
    factoryOf(::GeneralLayersConfigFlowUseCase)
    factoryOf(::UpdateGeneralLayerConfigUseCase)
    factoryOf(::UpdateSelectedModeConfigUseCase)
    factoryOf(::SelectedModeConfigFlowUseCase)
}