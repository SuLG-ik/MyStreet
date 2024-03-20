package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.formatter.MapObjectFieldFormatter

class FormatDescriptionUseCase(
    private val formatter: MapObjectFieldFormatter,
) {

    operator fun invoke(value: String): String {
        return formatter.formatDescription(value)
    }

}