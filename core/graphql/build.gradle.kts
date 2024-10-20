import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    library()
    apollo()
    buildKonfig()
}

deps {
    apollo(DependencyType.API)
    datetime(DependencyType.API)
    projectCoreAuth()
    projectErrorsShared()
}

apollo {
    service("service") {
        packageName.set(namespace())
        srcDir("src/commonMain/graphql/")
        generateApolloMetadata.set(true)
        mapScalarToUpload("Upload")
        mapScalar("DateTime", "kotlinx.datetime.LocalDateTime", "com.apollographql.adapter.datetime.KotlinxLocalDateTimeAdapter")
    }
}


buildkonfig {
    packageName = "ru.mystreet.core.graphql"
    // objectName = 'YourAwesomeConfig'
    // exposeObjectWithName = 'YourAwesomePublicConfig'
    defaultConfigs {
        this.buildConfigField(
            FieldSpec.Type.STRING,
            "MYSTREET_GRAPHQL_URL",
            configProperty("network.graphql", "http://api.mystreet.sulgik.ru/graphql"),
            const = true,
        )
    }
}