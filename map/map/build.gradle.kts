plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinxSerialization)
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
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(libs.kotlinx.coroutines)
            api(libs.mvikotlin.core)
            api(libs.mvikotlin.coroutines)
            api(libs.decompose.core)
            implementation(projects.uikit)
            implementation(libs.decompose.ui)
            api(libs.essenty.lifecycle)
            api(libs.essenty.coroutines)
            api(libs.koin.core)
            api(projects.core.component)
            api(projects.mapkitCompose)
            api(projects.map.shared)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "ru.mystreet.map.map"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}