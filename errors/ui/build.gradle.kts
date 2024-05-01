plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
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
            api(libs.mvikotlin.core)
            api(libs.mvikotlin.coroutines)
            api(libs.decompose.core)
            implementation(projects.uikit)
            implementation(libs.decompose.ui)
            api(libs.essenty.lifecycle)
            api(libs.essenty.coroutines)
            api(libs.kotlinx.collections)
            api(libs.koin.core)
            api(projects.core.component)
            implementation(libs.moko.resources)
            implementation(projects.core.time)
            implementation(libs.moko.resources.compose)
            api(projects.errors.shared)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "ru.mystreet.errors.ui"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}