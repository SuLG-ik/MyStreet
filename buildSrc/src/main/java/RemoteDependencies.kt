import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

fun KMPDependenciesScope.baseFeature(type: DependencyType = DependencyType.IMPLEMENTATION) {
    compose(type)
    mvikotlin(type)
    decomposeUI(type)
    koin(type)
    projectUIKit(type)
    projectCoreComponent(type)
}

fun KMPDependenciesScope.activityCompose(type: DependencyType = DependencyType.IMPLEMENTATION) {
    android("androidx.activity:activity-compose:${Versions.activity}", type)
}

fun KMPDependenciesScope.activity(type: DependencyType = DependencyType.IMPLEMENTATION) {
    android("androidx.activity:activity-ktx:${Versions.activity}", type)
}

fun KMPDependenciesScope.sqldelight(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("app.cash.sqldelight:runtime:${Versions.sqldelight}", type)
    common("app.cash.sqldelight:coroutines-extensions:${Versions.sqldelight}", type)
    common("app.cash.sqldelight:primitive-adapters:${Versions.sqldelight}", type)
}

fun KMPDependenciesScope.sqldelightDrivers(type: DependencyType = DependencyType.IMPLEMENTATION) {
    ios("app.cash.sqldelight:native-driver:${Versions.sqldelight}", type)
    android("app.cash.sqldelight:android-driver:${Versions.sqldelight}", type)
    desktop("app.cash.sqldelight:sqlite-driver:${Versions.sqldelight}", type)
}

fun KMPDependenciesScope.ktor(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("io.ktor:ktor-client-core:${Versions.ktor}", type)
    common("io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}", type)
    common("io.ktor:ktor-client-content-negotiation:${Versions.ktor}", type)
    common("io.ktor:ktor-client-logging:${Versions.ktor}", type)
    common("io.ktor:ktor-client-auth:${Versions.ktor}", type)
}

fun KMPDependenciesScope.ktorEngines(type: DependencyType = DependencyType.IMPLEMENTATION) {
    js("io.ktor:ktor-client-js:${Versions.ktor}", type)
    android("io.ktor:ktor-client-okhttp:${Versions.ktor}", type)
    ios("io.ktor:ktor-client-darwin:${Versions.ktor}", type)
}

fun KMPDependenciesScope.compose(type: DependencyType = DependencyType.IMPLEMENTATION) {
    android("androidx.compose.ui:ui-tooling:${Versions.compose}", type)
    common("org.jetbrains.compose.runtime:runtime:${Versions.composeMultiplatform}", type)
    common("org.jetbrains.compose.foundation:foundation:${Versions.composeMultiplatform}", type)
    common("org.jetbrains.compose.ui:ui:${Versions.composeMultiplatform}", type)
    common("org.jetbrains.compose.material3:material3:${Versions.composeMultiplatform}", type)
    common("org.jetbrains.compose.animation:animation:${Versions.composeMultiplatform}", type)
    common(
        dependency = "org.jetbrains.compose.components:components-resources:${Versions.composeMultiplatform}",
        type = type,
    )
    collections(type)
}

fun KMPDependenciesScope.collections(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("org.jetbrains.kotlinx:kotlinx-collections-immutable:${Versions.collections}", type)
}


fun KMPDependenciesScope.coroutines(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}", type)
}

fun KMPDependenciesScope.coroutinesGoogleServices(type: DependencyType = DependencyType.IMPLEMENTATION) {
    android("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines}", type)
}

fun KMPDependenciesScope.mvikotlin(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("com.arkivanov.mvikotlin:mvikotlin:${Versions.mvikotlin}", type)
    common("com.arkivanov.mvikotlin:mvikotlin-main:${Versions.mvikotlin}", type)
    common("com.arkivanov.mvikotlin:mvikotlin-logging:${Versions.mvikotlin}", type)
    common("com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:${Versions.mvikotlin}", type)
}

fun KMPDependenciesScope.decompose(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("com.arkivanov.decompose:decompose:${Versions.decompose}", type)
}

fun KMPDependenciesScope.decomposeUI(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("com.arkivanov.decompose:decompose:${Versions.decompose}", type)
    common("com.arkivanov.decompose:extensions-compose:${Versions.decompose}", type)
}

fun KMPDependenciesScope.datetime(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("org.jetbrains.kotlinx:kotlinx-datetime:${Versions.datetime}", type)
}

fun KMPDependenciesScope.serialization(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.serialization}", type)
}

fun KMPDependenciesScope.essenty(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("com.arkivanov.essenty:lifecycle:${Versions.essenty}", type)
    common("com.arkivanov.essenty:lifecycle-coroutines:${Versions.essenty}", type)
    common("com.arkivanov.essenty:back-handler:${Versions.essenty}", type)
}

fun KMPDependenciesScope.serializationJson(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.serialization}", type)
    common("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}", type)
}

fun KMPDependenciesScope.serializationJsonOkio(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("org.jetbrains.kotlinx:kotlinx-serialization-json-okio:${Versions.serialization}", type)
}

fun KMPDependenciesScope.koin(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("io.insert-koin:koin-core:${Versions.koin}", type)
    android("io.insert-koin:koin-android:${Versions.koin}", type)
}

fun KMPDependenciesScope.koinAnnotations(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("io.insert-koin:koin-annotations:${Versions.koinAnnotations}", type)
    with(project) {
        dependencies {
            add(
                "kspCommonMainMetadata",
                "io.insert-koin:koin-ksp-compiler:${Versions.koinAnnotations}"
            )
        }
        val kotlin = multiplatformExtension
        val commonMain = kotlin.sourceSets.get("commonMain")
        commonMain.kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        tasks.withType<KotlinCompilationTask<*>>().all {
            if (name != "kspCommonMainKotlinMetadata") {
                dependsOn("kspCommonMainKotlinMetadata")
            }
        }
    }
}

fun KMPDependenciesScope.crashlytics(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("co.touchlab.crashkios:crashlytics:${Versions.touchlabCrashlytocs}", type)
}

fun KMPDependenciesScope.analytics(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("dev.gitlive:firebase-analytics:${Versions.firebaseKmp}", type)
}

fun KMPDependenciesScope.firebaseAnalytics(type: DependencyType = DependencyType.IMPLEMENTATION) {
    android("com.google.firebase:firebase-analytics-ktx:${Versions.Firebase.analytics}", type)
}

fun KMPDependenciesScope.firebaseCrashlytics(type: DependencyType = DependencyType.IMPLEMENTATION) {
    android("com.google.firebase:firebase-crashlytics-ktx:${Versions.Firebase.crashlytics}", type)
}

fun KMPDependenciesScope.firebaseRemoteConfig(type: DependencyType = DependencyType.IMPLEMENTATION) {
    android("com.google.firebase:firebase-config:${Versions.Firebase.remoteConfig}", type)
    common("dev.gitlive:firebase-config:${Versions.firebaseKmp}", type)
}

fun KMPDependenciesScope.firebaseMessaging(type: DependencyType = DependencyType.IMPLEMENTATION) {
    android("com.google.firebase:firebase-messaging-ktx:${Versions.Firebase.messaging}", type)
    common("dev.gitlive:firebase-messaging:${Versions.firebaseKmp}", type)
}

fun KMPDependenciesScope.logging(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("io.github.oshai:kotlin-logging:${Versions.logging}", type)
    android("org.slf4j:slf4j-api:${Versions.slf4j}", type)
    android("org.slf4j:slf4j-simple:${Versions.slf4j}", type)
}

fun KMPDependenciesScope.datastore(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("androidx.datastore:datastore-preferences-core:${Versions.datastore}", type)
    android("androidx.datastore:datastore-preferences:${Versions.datastore}", type)
}

fun KMPDependenciesScope.mokoPermissions(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("dev.icerock.moko:permissions:${Versions.mokoPermissions}", type)
}

fun KMPDependenciesScope.mokoPermissionsUI(type: DependencyType = DependencyType.IMPLEMENTATION) {
    mokoPermissions(type)
    common("dev.icerock.moko:permissions-compose:${Versions.mokoPermissions}", type)
}

fun KMPDependenciesScope.mokoResources(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("dev.icerock.moko:resources:${Versions.mokoResources}", type)
}

fun KMPDependenciesScope.mokoResourcesUI(type: DependencyType = DependencyType.IMPLEMENTATION) {
    mokoResources(type)
    common("dev.icerock.moko:resources-compose:${Versions.mokoResources}", type)
}

fun KMPDependenciesScope.compottie(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("io.github.alexzhirkevich:compottie:${Versions.compottie}", type)
}

fun KMPDependenciesScope.lifecycle(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("org.jetbrains.androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}", type)
}

fun KMPDependenciesScope.lifecycleCompose(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("org.jetbrains.androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}", type)
}

fun KMPDependenciesScope.coil(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("io.coil-kt.coil3:coil:${Versions.coil}", type)
    common("io.coil-kt.coil3:coil-compose:${Versions.coil}", type)
    common("io.coil-kt.coil3:coil-network-ktor2:${Versions.coil}", type)
    common("io.coil-kt.coil3:coil-svg:${Versions.coil}", type)
}

fun KMPDependenciesScope.splash(type: DependencyType = DependencyType.IMPLEMENTATION) {
    android("androidx.core:core-splashscreen:${Versions.splash}", type)
}


fun KMPDependenciesScope.startup(type: DependencyType = DependencyType.IMPLEMENTATION) {
    android("androidx.startup:startup-runtime:${Versions.startup}", type)
}

fun KMPDependenciesScope.atomicfu(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("org.jetbrains.kotlinx:atomicfu:${Versions.atomicfu}", type)
}


fun KMPDependenciesScope.inAppReview(type: DependencyType = DependencyType.IMPLEMENTATION) {
    android("com.google.android.play:review:${Versions.inAppReview}", type)
    android("com.google.android.play:review-ktx:${Versions.inAppReview}", type)
}

fun KMPDependenciesScope.arrowCore(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("io.arrow-kt:arrow-core:${Versions.arrow}", type)
}

fun KMPDependenciesScope.apollo(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("com.apollographql.apollo:apollo-runtime:${Versions.apollo}", type)
    common("com.apollographql.adapters:apollo-adapters-core:${Versions.apolloAdapters}", type)
    common(
        "com.apollographql.adapters:apollo-adapters-kotlinx-datetime:${Versions.apolloAdapters}",
        type
    )
}

fun KMPDependenciesScope.mapkit(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("ru.sulgik.mapkit:yandex-mapkit-kmp:${Versions.Mapkit.kmp}", type)
    common("ru.sulgik.mapkit:yandex-mapkit-kmp-moko:${Versions.Mapkit.kmp}", type)
}

fun KMPDependenciesScope.mapkitUI(type: DependencyType = DependencyType.IMPLEMENTATION) {
    mapkit(type)
    common("ru.sulgik.mapkit:yandex-mapkit-kmp-compose:${Versions.Mapkit.kmp}", type)
}

fun KMPDependenciesScope.paging(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("app.cash.paging:paging-common:${Versions.paging}", type)
}

fun KMPDependenciesScope.pagingUI(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("app.cash.paging:paging-compose-common:${Versions.paging}", type)
}

fun KMPDependenciesScope.store5(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("org.mobilenativefoundation.store:store5:${Versions.store5}", type)
}

fun KMPDependenciesScope.peekaboo(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("io.github.onseok:peekaboo-image-picker:${Versions.peekaboo}",  type)
    common("io.github.onseok:peekaboo-ui:${Versions.peekaboo}",  type)
}

fun KMPDependenciesScope.reflect(type: DependencyType = DependencyType.IMPLEMENTATION) {
    common("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}",  type)
}

fun KMPDependenciesScope.gmsLocation(type: DependencyType = DependencyType.IMPLEMENTATION) {
    android("com.google.android.gms:play-services-location:${Versions.gmsLocation}",  type)
}

