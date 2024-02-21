package ru.mystreet.map.data.validator

import ru.mystreet.map.domain.entity.FieldError.FieldIsEmpty
import ru.mystreet.map.domain.entity.FieldError.IncorrectInput
import ru.mystreet.map.domain.entity.TitleField
import ru.mystreet.map.domain.validator.MapObjectFieldValidator

class RegexMapObjectFieldValidator : MapObjectFieldValidator {

    override fun validateTitle(value: String): TitleField {
        if (value.isEmpty())
            return TitleField(value, FieldIsEmpty)
        if (!value.matches(TITLE_REGEX))
            return TitleField(value, IncorrectInput)

        return TitleField(value)
    }

    companion object {
        val TITLE_REGEX = "[0-9а-яА-Яa-zA-Z\\s-,.]*".toRegex()
    }
}