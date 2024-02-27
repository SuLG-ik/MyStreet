package ru.mystreet.map.map

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.map.map.domain.usecase.GetAllMapObjectsUseCase
import ru.mystreet.map.map.domain.usecase.GetCategoriesDifferUseCase
import ru.mystreet.map.map.presentation.MapObjectsStore
import ru.mystreet.map.map.presentation.MapObjectsStoreImpl

val mapModule = module {
    factoryOf(::GetAllMapObjectsUseCase)
    factoryOf(::GetCategoriesDifferUseCase)
    factoryOf(::MapObjectsStoreImpl) bind MapObjectsStore::class
}