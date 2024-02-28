package ru.mystreet.imagepicker.domain.entity

data class SelectedImages(
    val maxCount: Int,
    val count: Int = 0,
    val images: List<ImageItem> = emptyList(),
)