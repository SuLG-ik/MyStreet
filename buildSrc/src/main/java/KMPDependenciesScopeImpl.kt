import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

class KMPDependenciesScopeImpl(
    override val project: Project,
    private val extension: KotlinMultiplatformExtension,
) : KMPDependenciesScope {

    override fun common(configure: KMPDependencyHandler.() -> Unit) {
        configure(SourceSets.COMMON, configure)
    }

    override fun android(configure: KMPDependencyHandler.() -> Unit) {
        if (!BuildParams.Build.supports(SourceSets.ANDROID)) return
        configure(SourceSets.ANDROID, configure)
    }

    override fun ios(configure: KMPDependencyHandler.() -> Unit) {
        if (!BuildParams.Build.supports(SourceSets.IOS)) return
        configure(SourceSets.IOS, configure)
    }

    override fun desktop(configure: KMPDependencyHandler.() -> Unit) {
        if (!BuildParams.Build.supports(SourceSets.DESKTOP)) return
        configure(SourceSets.DESKTOP, configure)
    }

    override fun js(configure: KMPDependencyHandler.() -> Unit) {
        if (!BuildParams.Build.supports(SourceSets.JS)) return
        configure(SourceSets.JS, configure)
    }

    private fun configure(sourceSet: SourceSets, configure: KMPDependencyHandler.() -> Unit) {
        val main = extension.sourceSets.getOrCreate("${sourceSet.prefix}Main")
        SourceSetBasedKMPDependencyHandler(main, main, main).apply(configure)
    }

    private fun NamedDomainObjectContainer<KotlinSourceSet>.getOrCreate(name: String): KotlinSourceSet {
        return extension.sourceSets.findByName(name)
            ?: extension.sourceSets.create(name)
    }

}