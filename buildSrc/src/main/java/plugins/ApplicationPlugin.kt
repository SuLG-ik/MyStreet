package plugins

class ApplicationPlugin : PluginsApplyPlugin(plugins) {
    companion object {
        internal const val ID = "android.application"
        private val plugins = listOf(
            "com.android.application",
            "org.jetbrains.kotlin.multiplatform",
            "org.jetbrains.compose",
            "org.jetbrains.kotlin.plugin.compose",
            "com.google.firebase.crashlytics",
            "com.google.gms.google-services",
        )
    }
}
