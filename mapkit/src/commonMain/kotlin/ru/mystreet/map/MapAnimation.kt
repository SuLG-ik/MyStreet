package ru.mystreet.map

data class MapAnimation(
    val type: Type,
    val duration: Float,
) {

    enum class Type {
        SMOOTH,
        LINEAR
    }

}