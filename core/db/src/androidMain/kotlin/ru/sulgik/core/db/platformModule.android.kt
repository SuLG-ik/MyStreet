package ru.sulgik.core.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.mystreet.core.db.Database

actual val platformDbModule: Module = module {
    singleOf(::createDriver)
}

private fun createDriver(context: Context): SqlDriver {
    return AndroidSqliteDriver(Database.Schema, context, "main.db")
}