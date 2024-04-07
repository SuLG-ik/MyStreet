package ru.mystreet.map.domain.entity

import kotlinx.datetime.LocalDateTime


class MapObjectReview(
    val id: Long,
    val title: String?,
    val text: String?,
    val rating: Int,
    val createdDate: LocalDateTime,
    val author: ReviewAuthor?,
)

data class ReviewAuthor(
    val name: String,
)