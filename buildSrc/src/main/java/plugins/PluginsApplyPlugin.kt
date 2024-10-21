package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class PluginsApplyPlugin(
    private val plugins: List<String>,
) : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                this@PluginsApplyPlugin.plugins.forEach {
                    apply(it)
                }
            }
        }
    }

}