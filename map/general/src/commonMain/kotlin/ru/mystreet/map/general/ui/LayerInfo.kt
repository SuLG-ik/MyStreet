package ru.mystreet.map.general.ui

import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import ru.mystreet.map.domain.entity.GeneralLayerType
import ru.mystreet.uikit.MR

enum class LayerInfo(
    val title: StringResource,
    val image: ImageResource,
) {
    Bench(MR.strings.bench, MR.images.bench),
    Bower(MR.strings.bower, MR.images.bower),
    Fountain(MR.strings.fountain, MR.images.fountain),
    GreenArea(MR.strings.green_zone, MR.images.green_zone),
    Monument(MR.strings.monument, MR.images.monument),
    Playground(MR.strings.playground, MR.images.playground),
    PublicWC(MR.strings.public_wc, MR.images.public_wc),
    StreetLight(MR.strings.streetlight, MR.images.streetlight)
}

fun GeneralLayerType.toUI(): LayerInfo {
    return when (this) {
        GeneralLayerType.Bench -> LayerInfo.Bench
        GeneralLayerType.Playground -> LayerInfo.Playground
        GeneralLayerType.StreetLight -> LayerInfo.StreetLight
        GeneralLayerType.Monument -> LayerInfo.Monument
        GeneralLayerType.Fountain -> LayerInfo.Fountain
        GeneralLayerType.Bower -> LayerInfo.Bower
        GeneralLayerType.GreenArea -> LayerInfo.GreenArea
        GeneralLayerType.PublicWC -> LayerInfo.PublicWC
    }
}
