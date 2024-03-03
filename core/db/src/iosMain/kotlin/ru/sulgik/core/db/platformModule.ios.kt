package ru.sulgik.core.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.mystreet.core.db.Database

actual val platformDbModule: Module = module {
    singleOf(::createDriver)
}

private fun createDriver():SqlDriver {
    return NativeSqliteDriver(Database.Schema, "main.db")
}