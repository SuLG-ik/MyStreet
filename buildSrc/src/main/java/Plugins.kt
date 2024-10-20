import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec
import plugins.ApplicationPlugin
import plugins.MokoResourcesPlugin
import plugins.LibraryPlugin
import plugins.LibraryUIPlugin

fun PluginDependenciesSpec.application(): PluginDependencySpec = id(ApplicationPlugin.ID)
fun PluginDependenciesSpec.library(): PluginDependencySpec = id(LibraryPlugin.ID)
fun PluginDependenciesSpec.libraryUI(): PluginDependencySpec = id(LibraryUIPlugin.ID)

fun PluginDependenciesSpec.buildKonfig(): PluginDependencySpec = id("com.codingfeline.buildkonfig")
fun PluginDependenciesSpec.ksp(): PluginDependencySpec = id("com.google.devtools.ksp")
fun PluginDependenciesSpec.apollo(): PluginDependencySpec = id("com.apollographql.apollo")
fun PluginDependenciesSpec.cocoapods(): PluginDependencySpec =
    id("org.jetbrains.kotlin.native.cocoapods")
fun PluginDependenciesSpec.atomicfu(): PluginDependencySpec =
    id("org.jetbrains.kotlin.plugin.atomicfu")

fun PluginDependenciesSpec.sqldelight(): PluginDependencySpec =
    id("app.cash.sqldelight")

fun PluginDependenciesSpec.mokoResources(): PluginDependencySpec = id(MokoResourcesPlugin.ID)


fun PluginDependenciesSpec.serialization(): PluginDependencySpec =
    id("org.jetbrains.kotlin.plugin.serialization")