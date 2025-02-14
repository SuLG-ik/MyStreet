import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.psi.stubs.impl.serialize

plugins {
    libraryUI()
    buildKonfig()
    cocoapods()
    mokoResources()
}

namespaceSuffix = "app"

deps {
    compose(DependencyType.API)
    decomposeUI(DependencyType.API)
    essenty(DependencyType.API)
    mvikotlin(DependencyType.API)
    koin(DependencyType.API)
    mokoPermissions(DependencyType.API)
    mokoResources(DependencyType.API)
    serialization(DependencyType.API)
    coroutines(DependencyType.API)

    projectCoreComponent(DependencyType.API)
    projectCoreDatastore(DependencyType.API)
    projectMapkitCompose(DependencyType.API)
    projectRoot(DependencyType.API)
    projectCoreCrashlytics(DependencyType.API)
    projectCoreTime(DependencyType.API)
    projectCoreDb(DependencyType.API)
    projectCoreAuth(DependencyType.API)
    projectCoreCoil(DependencyType.API)
    projectCoreKtor(DependencyType.API)
    projectCoreGraphql()
    projectUIKit()
    projectFeatureDialogs()
}

kotlin {
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        pod("YandexMapsMobile", Versions.Mapkit.library)
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
            configProperty("ymk.api-key", ""),
            const = true
        )
    }
}