package ru.mystreet.map.domain.entity

import ru.mystreet.uikit.ValidatedField

data class AddMapObjectReviewField(
    val title: ValidatedField<FieldError>,
    val content: ValidatedField<FieldError>,
    val rating: Int,
    val isAuthorHidden: Boolean,
)