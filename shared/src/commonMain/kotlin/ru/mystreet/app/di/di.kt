package ru.mystreet.app.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.mystreet.app.store.mviModule
import ru.mystreet.core.datastore.platformDataStoreModule
import ru.mystreet.root.rootModule

val appModule = module {
    includes(
        platformYandexMapModule,
        platformMokoAssetsStore,
        platformDataStoreModule,
        mviModule,

        rootModule,
    )
}

expect val platformYandexMapModule: Module