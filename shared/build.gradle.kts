import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxSerialization)
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

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true

            export(libs.decompose.core)
            export(libs.essenty.lifecycle)
            export(libs.moko.resources)
            export(project(":mapkit-compose"))
            export(project(":mapkit"))
        }
        pod("YandexMapsMobile", libs.versions.yandex.mapkit.get())
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            api(libs.moko.permissions)
            api(libs.mvikotlin.core)
            api(libs.mvikotlin.main)
            api(libs.mvikotlin.logging)
            api(libs.mvikotlin.coroutines)
            api(libs.decompose.core)
            implementation(libs.decompose.ui)
            api(libs.essenty.lifecycle)
            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.coroutines)
            api(libs.datastore.preferences)
            implementation(libs.moko.resources)
            implementation(libs.moko.resources.compose)
            api(libs.koin.core)
            api(projects.mapkitCompose)

            api(projects.core.datastore)

            api(projects.root)
            api(projects.uikit)
            api(projects.core.graphql)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.koin.test)
        }
    }
}

android {
    namespace = "ru.mystreet.app"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}

buildkonfig {
    packageName = "ru.mystreet.app"
    // objectName = 'YourAwesomeConfig'
    // exposeObjectWithName = 'YourAwesomePublicConfig'
    defaultConfigs {
        this.buildConfigField(
            FieldSpec.Type.STRING,
            "API_KEY",
            System.getenv("map_api_key"),
            const = true
        )
    }
}