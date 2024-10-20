interface PropertyKey : NullablePropertyKey {
    override val defaultValue: String
}

interface NullablePropertyKey {

    val propertyName: String

    val defaultValue: String?
        get() = null
}