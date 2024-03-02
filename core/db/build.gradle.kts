plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqldelight)
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
            api(libs.sqldelight.driver.android)
        }
        commonMain.dependencies {
            api(libs.sqldelight.core)
            api(libs.sqldelight.coroutines)
            api(libs.koin.core)
            api(libs.kotlinx.datetime)
        }
        iosMain.dependencies {
            api(libs.sqldelight.driver.native)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "ru.mystreet.core.db"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}


sqldelight {
    databases {
        create("Database") {
            srcDirs("src/commonMain/sqldelight")
            packageName.set("ru.mystreet.core.db")
        }
    }
}