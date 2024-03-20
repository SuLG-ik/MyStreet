package ru.mystreet.map.domain.entity

import kotlinx.serialization.Serializable
import ru.mystreet.uikit.ValidatedField

@Serializable
data class EditMapObjectField(
    val id: Long,
    val category: MapObjectCategory,
    val title: ValidatedField<FieldError>,
    val description: ValidatedField<FieldError>,
    val tags: TagsField,
)