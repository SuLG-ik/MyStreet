import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

group = "ru.mystreet.gradle"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

dependencies {
    val kotlinVersion = "2.0.21" // https://github.com/JetBrains/kotlin/releases/tag/v2.0.21
    val gmsVersion = "4.4.2"
    val agpVersion = "8.7.0" // https://developer.android.com/build/releases/gradle-plugin#android-gradle-plugin-8.7.0
    val composeVersion = "1.7.0" // https://github.com/JetBrains/compose-multiplatform/releases/tag/v1.7.0-rc01
    val firebaseCrashlyticsVersion = "3.0.2"
    val kspVersion = "2.0.21-1.0.25"
    val apolloVersion = "4.0.1"
    val buildKonfigVersion = "0.15.2" // https://github.com/yshrsmz/BuildKonfig/releases/tag/v0.15.2
    val sqldelightVersion = "2.0.2"
    val mokoResourcesVersion = "0.24.3"

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("com.google.gms:google-services:$gmsVersion")
    implementation("com.android.tools.build:gradle:$agpVersion")
    implementation("com.google.firebase:firebase-crashlytics-gradle:$firebaseCrashlyticsVersion")
    implementation("org.jetbrains.kotlin.plugin.compose:org.jetbrains.kotlin.plugin.compose.gradle.plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    implementation("org.jetbrains.compose:compose-gradle-plugin:$composeVersion")
    implementation("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:$kspVersion")
    implementation("com.apollographql.apollo:apollo-gradle-plugin:$apolloVersion")
    implementation("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:$buildKonfigVersion")
    implementation("org.jetbrains.kotlin:atomicfu:$kotlinVersion")
    implementation("app.cash.sqldelight:gradle-plugin:$sqldelightVersion")
    implementation("dev.icerock.moko:resources-generator:$mokoResourcesVersion")
}


gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "android.application"
            implementationClass = "plugins.ApplicationPlugin"
        }
        register("androidLibraryUi") {
            id = "android.library.ui"
            implementationClass = "plugins.LibraryUIPlugin"
        }
        register("androidLibrary") {
            id = "android.library"
            implementationClass = "plugins.LibraryPlugin"
        }
        register("mokoResources") {
            id = "config.moko-resources"
            implementationClass = "plugins.MokoResourcesPlugin"
        }
    }
}