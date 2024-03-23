package ru.mystreet.map.map

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.map.map.presentation.FramedMapObjectsStore
import ru.mystreet.map.map.presentation.FramedMapObjectsStoreImpl

val mapModule = module {
    factoryOf(::FramedMapObjectsStoreImpl) bind FramedMapObjectsStore::class
}