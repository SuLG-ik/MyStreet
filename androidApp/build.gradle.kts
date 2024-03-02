plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "ru.mystreet.app.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "ru.mystreet.app.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
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
//    implementation(libs.gms.location)
    implementation(projects.shared)
    implementation(libs.splash)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)
}