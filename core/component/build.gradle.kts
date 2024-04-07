plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
            implementation(libs.kotlinx.coroutines)
            api(libs.mvikotlin.core)
            api(libs.mvikotlin.coroutines)
            api(libs.decompose.core)
            api(libs.kotlinx.datetime)
            api(libs.kotlinx.serialization)
            implementation(libs.decompose.ui)
            api(libs.essenty.lifecycle)
            api(libs.essenty.coroutines)
            api(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "ru.mystreet.core.component"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}