import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependency
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun Project.deps(configure: KMPDependenciesScope.() -> Unit = {}) {
    KMPDependenciesScopeImpl(this, multiplatformExtension).configure()
}

interface KMPDependencyHandler {

    fun implementation(dependency: Any)

    fun debugImplementation(dependency: Any)

    fun releaseImplementation(dependency: Any)

    fun api(dependency: Any)

    fun debugApi(dependency: Any)

    fun releaseApi(dependency: Any)

    fun projectImplementation(path: String)

    fun projectApi(path: String)

    fun api(dependency: String, configure: ExternalModuleDependency.() -> Unit)
    fun implementation(dependency: String, configure: ExternalModuleDependency.() -> Unit)
}

interface KMPDependenciesScope {

    val project: Project

    fun common(configure: KMPDependencyHandler.() -> Unit)

    fun android(configure: KMPDependencyHandler.() -> Unit)

    fun ios(configure: KMPDependencyHandler.() -> Unit)

    fun desktop(configure: KMPDependencyHandler.() -> Unit)

    fun js(configure: KMPDependencyHandler.() -> Unit)

}

class SourceSetBasedKMPDependencyHandler(
    private val mainSourceSet: KotlinSourceSet,
    private val debugSourceSet: KotlinSourceSet,
    private val releaseSourceSet: KotlinSourceSet,
) : KMPDependencyHandler {

    override fun projectImplementation(path: String) {
        mainSourceSet.dependencies {
            implementation(project(path))
        }
    }

    override fun projectApi(path: String) {
        mainSourceSet.dependencies {
            api(project(path))
        }
    }

    override fun implementation(dependency: Any) {
        mainSourceSet.dependencies {
            implementation(dependency)
        }
    }

    override fun implementation(
        dependency: String,
        configure: ExternalModuleDependency.() -> Unit,
    ) {
        mainSourceSet.dependencies {
            implementation(dependency, configure)
        }
    }

    override fun debugImplementation(dependency: Any) {
        debugSourceSet.dependencies {
            implementation(dependency)
        }
    }

    override fun releaseImplementation(dependency: Any) {
        releaseSourceSet.dependencies {
            implementation(dependency)
        }
    }

    override fun api(dependency: Any) {
        mainSourceSet.dependencies {
            api(dependency)
        }
    }


    override fun api(
        dependency: String,
        configure: ExternalModuleDependency.() -> Unit,
    ) {
        mainSourceSet.dependencies {
            api(dependency, configure)
        }
    }

    override fun debugApi(dependency: Any) {
        debugSourceSet.dependencies {
            api(dependency)
        }
    }

    override fun releaseApi(dependency: Any) {
        releaseSourceSet.dependencies {
            api(dependency)
        }
    }
}


fun KMPDependenciesScope.common(dependency: Any, type: DependencyType) {
    common(kmpDependencyHandler(dependency, type))
}

fun KMPDependenciesScope.common(
    dependency: String,
    type: DependencyType,
    configure: ExternalModuleDependency.() -> Unit,
) {
    common(kmpDependencyHandler(dependency, type, configure))
}

fun KMPDependenciesScope.android(dependency: Any, type: DependencyType) {
    android(kmpDependencyHandler(dependency, type))
}

fun KMPDependenciesScope.android(
    dependency: String,
    type: DependencyType,
    configure: ExternalModuleDependency.() -> Unit,
) {
    android(kmpDependencyHandler(dependency, type, configure))
}

fun KMPDependenciesScope.ios(dependency: Any, type: DependencyType) {
    ios(kmpDependencyHandler(dependency, type))
}

fun KMPDependenciesScope.desktop(dependency: Any, type: DependencyType) {
    desktop(kmpDependencyHandler(dependency, type))
}

fun KMPDependenciesScope.js(dependency: Any, type: DependencyType) {
    js(kmpDependencyHandler(dependency, type))
}

fun kmpDependencyHandler(dependency: Any, type: DependencyType): KMPDependencyHandler.() -> Unit {
    return {
        when (type) {
            DependencyType.IMPLEMENTATION -> implementation(dependency)
            DependencyType.API -> api(dependency)
        }
    }
}

fun kmpDependencyHandler(
    dependency: String,
    type: DependencyType,
    configure: ExternalModuleDependency.() -> Unit,
): KMPDependencyHandler.() -> Unit {
    return {
        when (type) {
            DependencyType.IMPLEMENTATION -> implementation(dependency, configure)
            DependencyType.API -> api(dependency, configure)
        }
    }
}