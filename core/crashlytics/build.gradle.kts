import com.codingfeline.buildkonfig.compiler.FieldSpec

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
            implementation(libs.touchlab.crashlytics)
            implementation(projects.errors.shared)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "ru.mystreet.core.crashlytics"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}