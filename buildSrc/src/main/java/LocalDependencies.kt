fun KMPDependenciesScope.projectShared(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":shared", type)
}

fun KMPDependenciesScope.projectUIKit(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":uikit", type)
}

fun KMPDependenciesScope.projectCoreGraphql(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":core:graphql", type)
}

fun KMPDependenciesScope.projectCoreDatastore(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":core:datastore", type)
}

fun KMPDependenciesScope.projectCoreAuth(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":core:auth", type)
}

fun KMPDependenciesScope.projectCoreTime(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":core:time", type)
}

fun KMPDependenciesScope.projectCoreDb(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":core:db", type)
}
fun KMPDependenciesScope.projectCoreKtor(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":core:ktor", type)
}

fun KMPDependenciesScope.projectCoreCrashlytics(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":core:crashlytics", type)
}

fun KMPDependenciesScope.projectCoreCoil(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":core:coil", type)
}

fun KMPDependenciesScope.projectCoreComponent(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":core:component", type)
}

fun KMPDependenciesScope.projectAccount(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":account", type)
}

fun KMPDependenciesScope.projectSheet(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":sheet", type)
}

fun KMPDependenciesScope.projectMapkitCompose(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":mapkit-compose", type)
}

fun KMPDependenciesScope.projectMapGeneral(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":map:general", type)
}

fun KMPDependenciesScope.projectMapMap(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":map:map", type)
}

fun KMPDependenciesScope.projectMapMapObject(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":map:mapobject", type)
}

fun KMPDependenciesScope.projectMapRoot(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":map:root", type)
}

fun KMPDependenciesScope.projectMapShared(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":map:shared", type)
}

fun KMPDependenciesScope.projectMapParks(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":map:parks", type)
}

fun KMPDependenciesScope.projectMapTrash(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":map:trash", type)
}

fun KMPDependenciesScope.projectRoot(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":root", type)
}

fun KMPDependenciesScope.projectErrorsShared(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":errors:shared", type)
}

fun KMPDependenciesScope.projectErrorsUI(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":errors:ui", type)
}

fun KMPDependenciesScope.projectImagePicker(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":imagepicker", type)
}

fun KMPDependenciesScope.projectFeatureDialogs(type: DependencyType = DependencyType.IMPLEMENTATION) {
    project(":feature:dialogs", type)
}

private fun KMPDependenciesScope.project(path: String, type: DependencyType) {
    common {
        when (type) {
            DependencyType.IMPLEMENTATION ->
                projectImplementation(path)

            DependencyType.API ->
                projectApi(path)
        }
    }
}

