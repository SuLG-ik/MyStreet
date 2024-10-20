import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import java.util.Properties

private val localProperties = mutableMapOf<Project, Properties>()

fun Project.getProperty(key: PropertyKey): String {
    return getProperty(key.propertyName, key.defaultValue)
}

fun Project.getProperty(key: NullablePropertyKey): String? {
    val defaultValue = key.defaultValue
    return if (defaultValue == null) {
        getProperty(key.propertyName)
    } else {
        getProperty(key.propertyName, defaultValue)
    }
}

fun Project.configProperty(name: String): String? {
    return getProperty("project.config.$name")
}

fun Project.configProperty(name: String, defaultValue: String): String {
    return getProperty("project.config.$name", defaultValue)
}

/**
 * Get property from every where.
 * Order of property find priority:
 * 1. local.properties
 * 2. gradle.properties
 * 3. project extra properties
 * 4. root project extra properties
 * 5. Environment variables
 * 6. defaultValue parameter
 */
fun Project.getProperty(name: String, defaultValue: String): String {
    return getProperty(name) ?: defaultValue
}

/**
 * Get property from every where.
 * Order of property find priority:
 * 1. local.properties
 * 2. gradle.properties
 * 3. project extra properties
 * 4. root project extra properties
 * 5. Environment variables
 */
fun Project.getProperty(name: String): String? {
    return rootProject.getLocalProperty(name)
        ?: rootProject.findProperty(name)?.toString()
        ?: project.getExtraProperty(name)
        ?: rootProject.getExtraProperty(name)
        ?: getEnvironmentProperty(name)
}

fun Project.getExtraProperty(name: String): String? {
    return if (extra.has(name)) extra.get(name)?.toString() else null
}

fun getEnvironmentProperty(name: String, defaultValue: String): String? {
    return getEnvironmentProperty(name) ?: defaultValue
}

fun getEnvironmentProperty(name: String): String? {
    return System.getenv()[name]
}

fun Project.getLocalProperty(name: String, defaultValue: String): String {
    return getLocalProperty(name) ?: defaultValue
}

fun Project.getLocalProperty(name: String): String? {
    return getLocalProperties().getProperty(name)
}

fun Project.getLocalProperties(): Properties {
    return localProperties.getOrPut(rootProject) {
        loadLocalProperties()
    }
}

fun Project.loadLocalProperties(fileName: String = "local.properties"): Properties {
    return Properties().also { properties ->
        try {
            file(fileName).inputStream().use { properties.load(it) }
        } catch (e: Exception) {
            logger.info("$fileName not found, skip loading properties")
        }
    }
}
