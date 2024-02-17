package ru.mystreet.map.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class MapObjectTag(
    val id: Long,
    val title: String,
)