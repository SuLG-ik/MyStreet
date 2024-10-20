import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.apollo)
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
        }
        commonMain.dependencies {
            api(libs.apollo)
            api(libs.apollo.scalars)
            api(libs.kotlinx.datetime)
            implementation(projects.core.auth)
            implementation(projects.errors.shared)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "ru.mystreet.core.graphql"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}

apollo {
    service("service") {
        packageName.set("ru.mystreet.core.graphql")
        srcDir("src/commonMain/graphql/")
        generateApolloMetadata.set(true)
        mapScalarToUpload("Upload")
        mapScalar("DateTime", "kotlinx.datetime.LocalDateTime", "com.apollographql.apollo3.adapter.KotlinxLocalDateTimeAdapter")
    }
}


buildkonfig {
    packageName = "ru.mystreet.core.graphql"
    // objectName = 'YourAwesomeConfig'
    // exposeObjectWithName = 'YourAwesomePublicConfig'
    defaultConfigs {
        this.buildConfigField(
            FieldSpec.Type.STRING,
            "MYSTREET_GRAPHQL_URL",
            configProperty("network.graphql", "http://api.mystreet.sulgik.ru/graphql"),
            const = true,
        )
    }
}