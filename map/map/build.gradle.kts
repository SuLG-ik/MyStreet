plugins {
    libraryUI()
    apollo()
    atomicfu()
}

deps {
    baseFeature()

    mokoPermissionsUI()
    store5()
    atomicfu()
    koin()
    projectCoreDb()
    projectCoreTime()
    projectMapShared()
}

apollo {
    service("service") {
        packageName.set("ru.mystreet.map.data.model")
        dependsOn(project(":core:graphql"))
        srcDir("src/commonMain/graphql/")
    }
}