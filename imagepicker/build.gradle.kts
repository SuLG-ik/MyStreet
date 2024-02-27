plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.atomicfu)
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
            implementation(libs.compressor)
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
            api(libs.peekaboo.imagepicker)
            api(libs.peekaboo.ui)
            api(libs.atomicfu)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "ru.mystreet.imagepicker"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}