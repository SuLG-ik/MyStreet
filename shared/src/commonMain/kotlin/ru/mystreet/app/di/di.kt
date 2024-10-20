package ru.mystreet.app.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.mystreet.app.store.mviModule
import ru.mystreet.core.auth.authModule
import ru.mystreet.core.datastore.dataStoreModule
import ru.mystreet.core.time.dateTimeModule
import ru.mystreet.root.rootModule
import ru.sulgik.core.coil.coilModule
import ru.sulgik.core.db.dbModule
import ru.sulgik.core.graphql.graphqlModule
import ru.sulgik.core.ktor.ktorModule

val appModule = module {
    includes(
        graphqlModule,
        ktorModule,
        authModule,
        dataStoreModule,
        mviModule,
        coilModule,
        dbModule,
        dateTimeModule,
        rootModule,
        platformImageModule,
    )
}