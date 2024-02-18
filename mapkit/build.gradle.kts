plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
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

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "mapkit"
            isStatic = true
        }
        pod("YandexMapsMobile", libs.versions.yandex.mapkit.get())
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization)
            api(libs.moko.resources)
            implementation(libs.kotlinx.coroutines)
        }
        androidMain.dependencies {
            api(libs.yandex.mapkit)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "ru.mystreet.app"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}