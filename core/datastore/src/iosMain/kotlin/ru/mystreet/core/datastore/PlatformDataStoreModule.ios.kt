package ru.mystreet.core.datastore

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformDataStoreModule = module {
    singleOf(::IOSPlatformDataStoreFactory) bind DatastoreFactory::class
}