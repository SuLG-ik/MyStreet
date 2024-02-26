package ru.mystreet.map.domain.entity

import dev.icerock.moko.resources.ImageResource
import kotlinx.serialization.Serializable
import ru.mystreet.uikit.MR

@Serializable
enum class MapObjectCategory(
    val id: String,
    val image: ImageResource,
) {
    Bench("bench", MR.images.user_location),
    Playground("playground", MR.images.playground),
    StreetLight("streetlight", MR.images.user_location),
    Monument("monument", MR.images.user_location),
    Fountain("fountain", MR.images.user_location),
    Bower("bower", MR.images.user_location),
    GreenArea("green_area", MR.images.user_location),
    PublicWC("publicwc", MR.images.user_location),
    Trash("trash", MR.images.user_location);

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
