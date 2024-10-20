package ru.mystreet.map.domain.entity

import kotlinx.collections.immutable.ImmutableCollection
import kotlinx.collections.immutable.PersistentList
import kotlinx.serialization.Serializable
import ru.mystreet.uikit.ValidatedField

@Serializable
data class AddMapObjectField(
    val title: ValidatedField<FieldError>,
    val description: ValidatedField<FieldError>,
    val point: PointConfig,
    val tags: TagsField,
    val category: MapObjectCategory,
)

@Serializable
data class TagsField(
    val value: String,
    val isInputAvailable: Boolean,
    val tags: Tags,
    val suggestion: FieldSuggestion<String>,
) {

    @Serializable
    data class Tags(
        val tags: PersistentList<String>,
        val maxTags: Int,
    )

}

@Serializable
data class FieldSuggestion<T>(
    val isLoading: Boolean,
    val suggestions: ImmutableCollection<T>,
)

@Serializable
sealed interface FieldError {
    @Serializable
    object FieldIsEmpty : FieldError

    @Serializable
    object IncorrectInput : FieldError
}