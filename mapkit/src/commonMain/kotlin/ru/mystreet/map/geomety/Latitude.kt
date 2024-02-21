package ru.mystreet.map.geomety

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class Latitude(
    val value: Double
)

@Serializable
@JvmInline
value class Longitude(
    val value: Double
)