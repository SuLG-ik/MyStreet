object BuildParams {

    object Build {
        val SupportedSourceSets = listOf(SourceSets.IOS, SourceSets.ANDROID)

        fun supports(sourceSets: SourceSets): Boolean {
            return sourceSets in SupportedSourceSets
        }
    }

    object Android {
        const val MinSdk = 26
        const val CompileSdk = 26
        const val JvmTarget = "1.8"
    }

}