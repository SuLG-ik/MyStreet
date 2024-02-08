package ru.mystreet.map.domain

enum class GeneralLayerType(val id: String, val isEnabledByDefault: Boolean) {
    Bench("bench", true),
    Playground("playground", true),
    StreetLight("streetlight", true),
    Monument("monument", true),
    Fountain("fountain", true),
    Bower("bower", true),
    GreenZone("greenzone", true),
    PublicWC("publicwc", true),
}