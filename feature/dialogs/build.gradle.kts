plugins {
    libraryUI()
}

deps {
    baseFeature()
}

compose.resources {
    publicResClass = false
    packageOfResClass = namespace()
    generateResClass = always
}
