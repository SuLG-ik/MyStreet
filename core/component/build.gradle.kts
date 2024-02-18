plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
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

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../../iosApp/Podfile")
        framework {
            baseName = "corecomponent"
            isStatic = true
        }
    }
    
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