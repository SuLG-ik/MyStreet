package ru.mystreet.map.domain.entity

import dev.icerock.moko.resources.ImageResource
import kotlinx.serialization.Serializable
import ru.mystreet.uikit.MR

@Serializable
enum class MapObjectCategory(
    val id: String,
    val image: ImageResource,
) {
    Bench("bench", MR.images.map_icon_bench),
    Playground("playground", MR.images.map_icon_playground ),
    StreetLight("streetlight", MR.images.map_icon_streetlight),
    Monument("monument", MR.images.map_icon_monument),
    Fountain("fountain", MR.images.map_icon_fountain),
    Bower("bower", MR.images.map_icon_bower),
    GreenArea("green_area", MR.images.map_icon_green_area),
    PublicWC("public_wc", MR.images.map_icon_public_wc),
    Trash("trash", MR.images.map_icon_trash);

    companion object {

        fun fromId(id: String): MapObjectCategory {
            return when (id) {
                Bench.id -> Bench
                Playground.id -> Playground
                StreetLight.id -> StreetLight
                Monument.id -> Monument
                Fountain.id -> Fountain
                Bower.id -> Bower
                GreenArea.id -> GreenArea
                PublicWC.id -> PublicWC
                Trash.id -> Trash
                else -> throw IllegalArgumentException("MapObjectCategory with id = $id does not exits")
            }
        }
    }
}
