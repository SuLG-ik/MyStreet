plugins {
    libraryUI()
    apollo()
    mokoResources()
}

deps {
    baseFeature()

    paging(DependencyType.API)
    projectMapkitCompose(DependencyType.API)
    store5(DependencyType.API)
    mokoResources()
    projectErrorsShared(DependencyType.API)
    projectCoreDb()
    projectCoreTime()
    projectCoreGraphql()
    projectImagePicker()
    projectCoreDatastore()
}

apollo {
    service("service") {
        packageName.set("ru.mystreet.map.data.model")
        dependsOn(project(":core:graphql"))
        srcDir("src/commonMain/graphql/")
    }
}

multiplatformResources {
    resourcesPackage = "ru.mystreet.map"
}