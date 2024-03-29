package ru.mystreet.map.general

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.map.data.repository.DataStoreLayersConfigRepositoryImpl
import ru.mystreet.map.domain.repository.LayersConfigRepository
import ru.mystreet.map.general.domain.usecase.GeneralLayersConfigFlowUseCase
import ru.mystreet.map.general.domain.usecase.UpdateGeneralLayerConfigUseCase
import ru.mystreet.map.general.presentation.LayersConfigStore
import ru.mystreet.map.general.presentation.LayersConfigStoreImpl

val mapGeneralModule = module {
    factoryOf(::LayersConfigStoreImpl) bind LayersConfigStore::class
    factoryOf(::GeneralLayersConfigFlowUseCase)
    factoryOf(::UpdateGeneralLayerConfigUseCase)
}