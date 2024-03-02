package ru.sulgik.core.db

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.mystreet.core.db.Database
import ru.mystreet.core.db.MapFrame
import ru.mystreet.core.db.MapFrameQueries
import ru.mystreet.core.db.MapObjectPartQueries

val dbModule = module {
    includes(platformDbModule)
    singleOf(::createDatabase)
    singleOf(::createMapObjectPartQueries)
    singleOf(::createMapFrameQueries)
}

private fun createDatabase(
    driver: SqlDriver,
): Database {
    return Database(
        driver = driver,
        mapFrameAdapter = MapFrame.Adapter(
            updatedAtAdapter = LocalDateTimeAdapter,
            createdAtAdapter = LocalDateTimeAdapter,
        )
    )
}

private fun createMapObjectPartQueries(database: Database): MapObjectPartQueries {
    return database.mapObjectPartQueries
}

private fun createMapFrameQueries(database: Database): MapFrameQueries {
    return database.mapFrameQueries
}