package plugins

import org.gradle.api.Project

class MokoResourcesPlugin : PluginsApplyPlugin(plugins) {

    override fun apply(target: Project) {
        super.apply(target)

    }

    companion object {
        internal const val ID = "config.moko-resources"
        internal val plugins = listOf(
            "dev.icerock.mobile.multiplatform-resources",
        )
    }
}
