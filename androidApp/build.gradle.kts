import com.android.build.OutputFile

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.gms)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    val abiCodes = mapOf("x86" to 1, "x86_64" to 2, "arm64-v8a" to 3, "armeabi-v7a" to 4)
    namespace = "ru.mystreet.app"
    compileSdk = 34
    defaultConfig {
        applicationId = "ru.mystreet.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "0.0.1"
        splits {
            abi {

                // Enables building multiple APKs per ABI.
                isEnable = true

                // By default all ABIs are included, so use reset() and include to specify that you only
                // want APKs for x86 and x86_64.

                // Resets the list of ABIs for Gradle to create APKs for to none.
                reset()

                // Specifies a list of ABIs for Gradle to create APKs for.
                include(*(abiCodes.keys.toTypedArray()))

                // Specifies that you don't want to also generate a universal APK that includes all ABIs.
                isUniversalApk = false
            }
        }// Configures multiple APKs based on ABI.

    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        val env = System.getenv()
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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