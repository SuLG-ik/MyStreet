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
            api(libs.mvikotlin.core)
            api(libs.decompose.core)
            implementation(libs.decompose.ui)
            api(libs.essenty.lifecycle)
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