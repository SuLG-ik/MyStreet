plugins {
    library()
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.mapkit.core)
        }
    }
}