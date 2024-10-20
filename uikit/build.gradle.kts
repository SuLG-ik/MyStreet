plugins {
    libraryUI()
    mokoResources()
}

deps {
    compose(DependencyType.API)
    decomposeUI(DependencyType.API)

    arrowCore(DependencyType.API)
    coil(DependencyType.API)
    mokoResourcesUI(DependencyType.API)
    projectCoreCoil()
}

multiplatformResources {
    resourcesPackage = "ru.mystreet.uikit"
}