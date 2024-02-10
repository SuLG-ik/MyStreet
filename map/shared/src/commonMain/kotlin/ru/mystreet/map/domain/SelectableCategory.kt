package ru.mystreet.map.domain

data class SelectableCategory(
    val isSelected: Boolean,
    val position: Int,
    val type: SelectableCategoryType,
)