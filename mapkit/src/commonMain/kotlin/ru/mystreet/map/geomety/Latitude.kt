package ru.mystreet.map.geomety

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class Latitude(
    val value: Double,
) : Comparable<Latitude> {
    override fun compareTo(other: Latitude): Int {
        return value.compareTo(other.value)
    }
}

@Serializable
@JvmInline
value class Longitude(
    val value: Double,
) : Comparable<Longitude> {
    override fun compareTo(other: Longitude): Int {
        return value.compareTo(other.value)
    }
}