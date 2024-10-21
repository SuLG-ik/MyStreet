plugins {
    library()
    serialization()
}

deps {
    koin(DependencyType.API)
    datetime(DependencyType.API)
    serializationJson()
    coroutines(DependencyType.API)
    projectCoreDatastore(DependencyType.API)
}