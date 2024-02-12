package ru.mystreet.map.domain.entity

import kotlinx.serialization.Serializable

@Serializable
enum class SelectableCategoryType(
    val id: String
) {
    Bench("bench"),
    Playground("playground"),
    StreetLight("streetlight"),
    Monument("monument"),
    Fountain("fountain"),
    Bower("bower"),
    GreenZone("greenzone"),
    PublicWC("publicwc"),
    Trash("trash"),
}