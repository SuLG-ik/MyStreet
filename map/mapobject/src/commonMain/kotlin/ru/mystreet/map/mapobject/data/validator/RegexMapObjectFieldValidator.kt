package ru.mystreet.map.data.validator

import ru.mystreet.map.domain.entity.FieldError
import ru.mystreet.map.domain.entity.FieldError.FieldIsEmpty
import ru.mystreet.map.domain.entity.FieldError.IncorrectInput
import ru.mystreet.map.domain.validator.MapObjectFieldValidator

class RegexMapObjectFieldValidator : MapObjectFieldValidator {

    override fun validateTitle(value: String): FieldError? {
        if (value.isEmpty())
            return FieldIsEmpty
        if (!value.matches(TITLE_REGEX))
            return IncorrectInput

        return null
    }

    override fun validateDescription(value: String): FieldError? {
        return null
    }

    companion object {
        val TITLE_REGEX = "[0-9а-яА-Яa-zA-Z\\s-,.]*".toRegex()
    }
}