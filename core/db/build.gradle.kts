plugins {
    library()
    sqldelight()
}

deps {
    sqldelight(DependencyType.API)
    sqldelightDrivers()
    koin()
    datetime()
}

sqldelight {
    databases {
        create("Database") {
            srcDirs("src/commonMain/sqldelight")
            packageName.set("ru.mystreet.core.db")
        }
    }
    linkSqlite = true
}