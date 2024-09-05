package ru.mystreet.map

class Polygon(
    val outerRing: LinearRing,
    val innerRings: List<LinearRing>,
)