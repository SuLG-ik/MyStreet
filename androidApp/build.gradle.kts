plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.gms)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "ru.mystreet.app"
    compileSdk = 34
    defaultConfig {
        applicationId = "ru.mystreet.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        val env = System.getenv()
        getByName("release") {
            isMinifyEnabled = false
            resValue("string", "mystreet_host", env["MYSTREET_HOST"] ?: "")
        }
        getByName("debug") {
            resValue("string", "mystreet_host", env["MYSTREET_HOST"] ?: "")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.gms.location)
    implementation(compose.ui)
    implementation(compose.material3)
    implementation(projects.shared)
    implementation(libs.splash)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.firebase))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
    debugImplementation(libs.compose.ui.tooling)
}