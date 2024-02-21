package ru.mystreet.map.domain.entity

import kotlinx.serialization.Serializable
import ru.mystreet.map.geomety.Point

@Serializable
data class AddMapObjectField(
    val title: TitleField,
    val description: String,
    val point: Point,
    val tags: TagsField,
    val category: MapObjectCategory,
) {

}


@Serializable
data class TagsField(
    val value: String,
    val isInputAvailable: Boolean,
    val tags: Tags,
    val suggestion: FieldSuggestion<String>,
) {

    @Serializable
    data class Tags(
        val tags: List<String>,
        val maxTags: Int,
    )

}

@Serializable
class TitleField(
    val value: String,
    val error: FieldError? = null,
)

@Serializable
class FieldSuggestion<T>(
    val isLoading: Boolean,
    val suggestions: List<T>,
)

@Serializable
sealed interface FieldError {
    @Serializable
    object FieldIsEmpty : FieldError

    @Serializable
    object IncorrectInput : FieldError
}