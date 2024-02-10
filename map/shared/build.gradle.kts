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
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            implementation(libs.mvikotlin.core)
            implementation(libs.mvikotlin.coroutines)
            implementation(libs.decompose.ui)
            implementation(libs.kotlinx.coroutines)
            implementation(libs.decompose.core)
            implementation(projects.core.component)
            implementation(projects.mapkitCompose)
            implementation(projects.uikit)
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