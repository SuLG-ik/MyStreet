package ru.mystreet.map.domain.formatter

interface MapObjectFieldFormatter {

    fun formatTitle(value: String): String

    fun formatDescription(value: String): String
}