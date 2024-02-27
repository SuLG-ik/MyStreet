package ru.mystreet.app.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.mystreet.app.store.mviModule
import ru.mystreet.core.datastore.dataStoreModule
import ru.mystreet.core.datastore.platformDataStoreModule
import ru.mystreet.root.rootModule
import ru.sulgik.core.coil.coilModule
import ru.sulgik.core.graphql.graphqlModule

val appModule = module {
    includes(
        graphqlModule,
        platformYandexMapModule,
        platformMokoAssetsStore,
        dataStoreModule,
        mviModule,
        coilModule,

        rootModule,
    )
}

expect val platformYandexMapModule: Module