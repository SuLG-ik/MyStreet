package plugins

import org.gradle.api.Project
import multiplatformExtension
import androidLibrary
import namespace
import AppTarget
import org.gradle.api.JavaVersion
import BuildParams
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

class LibraryPlugin : PluginsApplyPlugin(plugins) {

    private val supportedTargets = listOf(AppTarget.Ios, AppTarget.Android)

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    override fun apply(target: Project) {
        super.apply(target)
        with(target) {
            with(multiplatformExtension) {
                supportedTargets.forEach { it.configure(this) }
                compilerOptions {
                    freeCompilerArgs.add("-Xexpect-actual-classes")
                    freeCompilerArgs.add("-opt-in=kotlinx.cinterop.ExperimentalForeignApi")
                }
            }

            androidLibrary {
                namespace = namespace()
                compileSdk = BuildParams.Android.CompileSdk
                defaultConfig {
                    minSdk = BuildParams.Android.MinSdk
                }
                compileOptions {
                    val version = JavaVersion.toVersion(BuildParams.Android.JvmTarget)
                    sourceCompatibility = version
                    targetCompatibility = version
                }
            }
        }
    }

    companion object {
        internal const val ID = "android.library"
        internal val plugins = listOf(
            "com.android.library",
            "org.jetbrains.kotlin.multiplatform",
        )
    }
}