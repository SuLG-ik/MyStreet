plugins {
    library()
}

deps {
    koin()
    datastore(DependencyType.API)
    serializationJsonOkio()
}