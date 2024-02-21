package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.TitleField

class FormatAndValidateTitleUseCase(
    private val formatTitleUseCase: FormatTitleUseCase,
    private val validateTitleUseCase: ValidateTitleUseCase,
) {

    operator fun invoke(value: String): TitleField {
        return validateTitleUseCase(formatTitleUseCase(value))
    }

}