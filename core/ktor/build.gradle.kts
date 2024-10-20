import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    library()
    buildKonfig()
}

deps {
    koin()
    ktor(DependencyType.API)
    ktorEngines()
    projectCoreAuth()
}

buildkonfig {
    packageName = "ru.mystreet.core.ktor"
    // objectName = 'YourAwesomeConfig'
    // exposeObjectWithName = 'YourAwesomePublicConfig'
    defaultConfigs {
        this.buildConfigField(
            FieldSpec.Type.STRING,
            "MYSTREET_API_URL",
            configProperty("network.rest", "http://api.mystreet.sulgik.ru"),
            const = true,
        )
    }
}