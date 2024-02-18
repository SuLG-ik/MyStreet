package ru.mystreet.root

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.map.root.mapHostModule
import ru.mystreet.root.presentation.AppRootInitializingStore
import ru.mystreet.root.presentation.AppRootInitializingStoreImpl

val rootModule = module {
    includes(mapHostModule)
    factoryOf(::AppRootInitializingStoreImpl) bind AppRootInitializingStore::class
}