package ru.mystreet.map.domain.entity

import kotlinx.serialization.Serializable

@Serializable
enum class MapObjectCategory(
    val id: String,
) {
    Bench("bench"),
    Playground("playground"),
    StreetLight("streetlight"),
    Monument("monument"),
    Fountain("fountain"),
    Bower("bower"),
    GreenArea("green_area"),
    PublicWC("publicwc"),
    Trash("trash");
    companion object {

        fun fromId(id: String): MapObjectCategory {
            return when (id) {
                Bench.id -> Bench
                Playground.id -> Playground
                StreetLight.id -> StreetLight
                Monument.id -> Monument
                Fountain.id -> Fountain
                Bench.id -> Bower
                GreenArea.id -> GreenArea
                PublicWC.id -> PublicWC
                Trash.id -> Trash
                else -> throw IllegalArgumentException("MapObjectCategory with id = $id does not exits")
            }
        }
    }
}
