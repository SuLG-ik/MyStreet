package ru.mystreet.map.domain.entity

import kotlinx.datetime.LocalDateTime

data class MapFrame(
    val column: Int,
    val row: Int,
)

data class SavedMapFrame(
    val column: Int,
    val row: Int,
    val updatedAt: LocalDateTime,
)