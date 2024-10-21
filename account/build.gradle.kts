plugins {
    libraryUI()
    apollo()
    ksp()
}

deps {
    baseFeature()

    koinAnnotations()
    mokoResourcesUI()
    projectCoreDatastore()
    projectCoreAuth()
    projectCoreGraphql()
}


apollo {
    service("service") {
        packageName.set("ru.mystreet.map.account")
        dependsOn(project(":core:graphql"))
        srcDir("src/commonMain/graphql/")
    }
}