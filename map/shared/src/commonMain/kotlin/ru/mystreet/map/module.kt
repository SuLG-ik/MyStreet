package ru.mystreet.map

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.map.data.converter.GraphqlMapObjectsConverter
import ru.mystreet.map.data.repository.GraphqlMapObjectsRepository
import ru.mystreet.map.domain.entity.MapObject
import ru.mystreet.map.domain.repository.MapObjectsRepository
import ru.mystreet.map.presentation.EditMapSelectCategoryStore
import ru.mystreet.map.presentation.EditMapSelectCategoryStoreImpl
import ru.mystreet.map.presentation.EditMapStore
import ru.mystreet.map.presentation.EditMapStoreImpl

val mapSharedModule = module {
    factoryOf(::EditMapStoreImpl) bind EditMapStore::class
    factoryOf(::EditMapSelectCategoryStoreImpl) bind EditMapSelectCategoryStore::class
    singleOf(::GraphqlMapObjectsConverter)
    singleOf(::GraphqlMapObjectsRepository) bind MapObjectsRepository::class
}