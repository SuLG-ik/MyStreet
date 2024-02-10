package ru.mystreet.map

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.map.presentation.EditMapSelectCategoryStore
import ru.mystreet.map.presentation.EditMapSelectCategoryStoreImpl
import ru.mystreet.map.presentation.EditMapStore
import ru.mystreet.map.presentation.EditMapStoreImpl

val mapSharedModule = module {
    factoryOf(::EditMapStoreImpl) bind EditMapStore::class
    factoryOf(::EditMapSelectCategoryStoreImpl) bind EditMapSelectCategoryStore::class
}