package ru.mystreet.map.general.component

import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import ru.mystreet.uikit.MR

sealed interface LayerInfo {

    val layerId: String
    val title: StringResource
    val image: ImageResource

    object Bench : LayerInfo {
        override val layerId: String = "bench"
        override val title: StringResource
            get() = MR.strings.bench
        override val image: ImageResource
            get() = MR.images.bench
    }

    object Bower : LayerInfo {
        override val layerId: String = "bower"
        override val title: StringResource
            get() = MR.strings.bower
        override val image: ImageResource
            get() = MR.images.bower
    }

    object Fountain : LayerInfo {
        override val layerId: String = "fountain"
        override val title: StringResource
            get() = MR.strings.fountain
        override val image: ImageResource
            get() = MR.images.fountain
    }

    object GreenZone : LayerInfo {
        override val layerId: String = "greenzone"
        override val title: StringResource
            get() = MR.strings.green_zone
        override val image: ImageResource
            get() = MR.images.green_zone
    }

    object Monument : LayerInfo {
        override val layerId: String = "monument"
        override val title: StringResource
            get() = MR.strings.monument
        override val image: ImageResource
            get() = MR.images.monument
    }

    object Playground : LayerInfo {
        override val layerId: String = "playground"
        override val title: StringResource
            get() = MR.strings.playground
        override val image: ImageResource
            get() = MR.images.playground
    }

    object PublicWC : LayerInfo {
        override val layerId: String = "publicwc"
        override val title: StringResource
            get() = MR.strings.public_wc
        override val image: ImageResource
            get() = MR.images.public_wc
    }

    object StreetLight : LayerInfo {
        override val layerId: String = "streetlight"
        override val title: StringResource
            get() = MR.strings.streetlight
        override val image: ImageResource
            get() = MR.images.streetlight
    }

}