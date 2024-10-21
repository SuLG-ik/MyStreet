package plugins

import org.gradle.api.Project

class LibraryUIPlugin : PluginsApplyPlugin(plugins) {

    override fun apply(target: Project) {
        super.apply(target)
    }

    companion object {
        internal const val ID = "android.library.ui"
        internal val plugins = listOf(
            LibraryPlugin.ID,
            "org.jetbrains.compose",
            "org.jetbrains.kotlin.plugin.compose",
            "org.jetbrains.kotlin.plugin.serialization",
        )
    }
}
