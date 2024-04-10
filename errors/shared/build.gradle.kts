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
        }
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            implementation(libs.paging.common)
            implementation(libs.mvikotlin.core)
            implementation(libs.mvikotlin.coroutines)
            implementation(libs.kotlinx.coroutines)
            implementation(libs.decompose.core)
            implementation(libs.kotlin.reflect)
            implementation(projects.core.component)
            implementation(projects.core.db)
            implementation(projects.core.time)
            implementation(libs.atomicfu)
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
dependencies {
    implementation(libs.firebase.crashlytics.buildtools)
}
