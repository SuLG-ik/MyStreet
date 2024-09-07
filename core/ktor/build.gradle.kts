import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.buildKonfig)
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
            implementation(libs.ktor.okhttp)
        }
        commonMain.dependencies {
            api(libs.koin.core)
            implementation(libs.ktor.auth)
            api(libs.ktor.core)
            implementation(libs.ktor.json)
            implementation(libs.ktor.serialization)
            implementation(projects.core.auth)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "ru.mystreet.core.ktor"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}


buildkonfig {
    packageName = "ru.mystreet.core.ktor"
    // objectName = 'YourAwesomeConfig'
    // exposeObjectWithName = 'YourAwesomePublicConfig'
    defaultConfigs {
        this.buildConfigField(
            FieldSpec.Type.STRING,
            "MYSTREET_API_URL",
            System.getenv()["MYSTREET_API_URL"] ?: "",
            const = true,
        )
    }
}