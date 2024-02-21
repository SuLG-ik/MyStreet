package ru.mystreet.core.datastore

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataStoreModule: Module = module {
    includes(platformDataStoreModule)
    singleOf(::DataStoreFactoryImpl) bind DatastoreFactory::class
}