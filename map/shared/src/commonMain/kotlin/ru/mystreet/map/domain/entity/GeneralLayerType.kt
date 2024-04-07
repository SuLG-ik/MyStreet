package ru.mystreet.map.domain.entity

enum class GeneralLayerType(val category: MapObjectCategory, val isEnabledByDefault: Boolean) {
    Bench(MapObjectCategory.Bench, true),
    Playground(MapObjectCategory.Playground, true),
    Installation(MapObjectCategory.Installation, true),
    Monument(MapObjectCategory.Monument, true),
    Fountain(MapObjectCategory.Fountain, true),
    Bower(MapObjectCategory.Bower, true),
    GreenArea(MapObjectCategory.GreenArea, true),
    PublicWC(MapObjectCategory.PublicWC, true),
    Other(MapObjectCategory.Other, true),
}