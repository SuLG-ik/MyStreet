package ru.mystreet.map.domain.entity


class MapObjectReview(
    val id: Long,
    val title: String?,
    val text: String?,
    val author: ReviewAuthor?,
)

data class ReviewAuthor(
    val name: String,
)