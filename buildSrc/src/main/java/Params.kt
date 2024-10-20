import org.gradle.api.Project

private var namespaces = mutableMapOf<String, String>()

var Project.namespaceSuffix: String
    get() {
        return namespaces[path] ?: ""
    }
    set(value)  {
        namespaces[path] = value
    }

fun Project.namespace(): String {
    val namespace = configProperty("app.namespace")
        ?: throw IllegalArgumentException("Namespace is not provided")
    if (namespaceSuffix.isBlank()) {
        namespaceSuffix = suffixFromPath()
    }
    return "$namespace.$namespaceSuffix"
}

private fun Project.suffixFromPath(): String {
    return path.split(":")
        .filterNot { it.isBlank() }
        .joinToString(".")
        .replace("-", "")
        .replace("_", "")
}