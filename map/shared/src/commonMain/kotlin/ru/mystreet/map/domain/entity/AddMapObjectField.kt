package ru.mystreet.map.domain.entity

import kotlinx.serialization.Serializable
import ru.mystreet.map.geomety.Point

@Serializable
data class AddMapObjectField(
    val title: String,
    val description: String,
    val point: Point,
    val tags: Tags,
    val category: MapObjectCategory,
) {
    @Serializable
    data class Tags(
        val tag: String,
        val tags: List<String>,
        val maxTags: Int,
        val isInputAvailable: Boolean,
    )
}