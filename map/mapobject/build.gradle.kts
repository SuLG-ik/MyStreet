plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.apollo)
    alias(libs.plugins.moko.resources)
}

kotlin {
    androidTarget()
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        androidMain.dependencies {
        }
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            implementation(libs.mvikotlin.core)
            implementation(libs.paging.compose)
            implementation(libs.mvikotlin.coroutines)
            implementation(libs.decompose.ui)
            implementation(libs.kotlinx.coroutines)
            implementation(libs.decompose.core)
            implementation(projects.core.component)
            implementation(projects.mapkitCompose)
            implementation(projects.uikit)
            implementation(projects.map.shared)
            api(projects.core.graphql)
            api(projects.imagepicker)
            implementation(projects.core.datastore)
            implementation(libs.moko.resources)
            implementation(libs.kotlinx.collections)
            implementation(libs.peekaboo.imagepicker)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "ru.mystreet.map.root"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}

apollo {
    service("service") {
        packageName.set("ru.mystreet.map.data.model")
        dependsOn(projects.core.graphql)
        srcDir("src/commonMain/graphql/")
    }
}

multiplatformResources {
    resourcesPackage = "ru.mystreet.map.mapobject"
}