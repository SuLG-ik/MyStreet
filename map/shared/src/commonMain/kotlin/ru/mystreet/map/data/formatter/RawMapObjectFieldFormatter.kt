package ru.mystreet.map.data.formatter

import ru.mystreet.map.domain.formatter.MapObjectFieldFormatter

class RawMapObjectFieldFormatter : MapObjectFieldFormatter {

    override fun formatTitle(value: String): String {
        return value.trimStart()
    }

    override fun formatDescription(value: String): String {
        return value.trimStart()
    }

}