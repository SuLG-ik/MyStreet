package ru.mystreet.map.domain.entity

data class SelectableCategory(
    val isSelected: Boolean,
    val position: Int,
    val type: MapObjectCategory,
)