enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "MyStreet"
include(":androidApp")
include(":shared")
include(":uikit")
include(":mapkit")
include(":mapkit-compose")
include(":root")
include(":imagepicker")
include(":account")
include(":sheet")

include(":map:root")
include(":map:map")
include(":map:general")
include(":map:shared")
include(":map:trash")
include(":map:parks")
include(":map:mapobject")

include(":errors:ui")
include(":errors:shared")

include(":core:component")
include(":core:datastore")
include(":core:graphql")
include(":core:coil")
include(":core:db")
include(":core:time")
include(":core:auth")
include(":core:ktor")