plugins {
    libraryUI()
    apollo()
    mokoResources()
}

deps {
    baseFeature()

    projectMapkitCompose()
    projectMapShared()
    projectCoreGraphql()
    projectImagePicker()
    mokoResources()
    projectErrorsShared()
    projectFeatureDialogs()
    pagingUI()
}

apollo {
    service("service") {
        packageName.set("ru.mystreet.map.data.model")
        dependsOn(project(":core:graphql"))
        srcDir("src/commonMain/graphql/")
    }
}

multiplatformResources {
    resourcesPackage = "ru.mystreet.map.mapobject"
}