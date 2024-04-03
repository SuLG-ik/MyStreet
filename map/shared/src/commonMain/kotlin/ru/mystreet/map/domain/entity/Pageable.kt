package ru.mystreet.map.domain.entity

import ru.mystreet.core.graphql.type.PageableInput

data class Pageable(
    val page: Int,
    val pageSize: Int
)

fun Pageable.next(): Pageable {
    return copy(page = page + 1)
}

fun Pageable.toInput(): PageableInput {
    return PageableInput(page = page, pageSize = pageSize)
}